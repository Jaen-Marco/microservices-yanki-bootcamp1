package com.microservices.project4.Yanki.service;

import com.microservices.project4.Yanki.exceptions.InsufficientFundsException;
import com.microservices.project4.Yanki.exceptions.TransactionFailedException;
import com.microservices.project4.Yanki.model.WalletTransaction;
import com.microservices.project4.Yanki.repository.WalletRepository;
import com.microservices.project4.Yanki.repository.WalletTransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class WalletTransactionService {

    @Autowired
    private final WalletTransactionRepository walletTransactionRepository;
    @Autowired
    private final WalletRepository walletRepository;
    @Autowired
    private final KafkaTemplate<String, WalletTransaction> kafkaTemplate;

    public void sendTransaction(WalletTransaction transaction) {
        kafkaTemplate.send("wallet_transactions", transaction);
    }

    public Flux<WalletTransaction> findAll() { return walletTransactionRepository.findAll(); }


    public Mono<WalletTransaction> findById(Long id) { return walletTransactionRepository.findById(id); }


    public Mono<WalletTransaction> save(WalletTransaction transaction) {
        return walletRepository.findByPhoneNumber(transaction.getSenderPhoneNumber())
                .filter(senderWallet -> senderWallet.getBalance() >= transaction.getAmount())
                .switchIfEmpty(Mono.error(new InsufficientFundsException("Saldo insuficiente para realizar la transacción")))
                .flatMap(senderWallet -> {
                    transaction.setTransactionStatus("PENDING");
                    // Envia la transacción al tópico de Kafka
                    return kafkaTemplate.send("wallet_transactions", transaction)
                            .onErrorResume(e -> Mono.error(new TransactionFailedException("Error al enviar la transacción a Kafka", e)))
                            .then(Mono.just(transaction));
                });
    }
}
