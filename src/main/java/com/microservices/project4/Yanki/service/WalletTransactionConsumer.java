package com.microservices.project4.Yanki.service;

import com.microservices.project4.Yanki.model.WalletTransaction;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class WalletTransactionConsumer {

    private final WalletTransactionService walletTransactionService;

    @KafkaListener(topics = "wallet_transactions", groupId = "wallet_group")
    public Mono<Void> processWalletTransaction(WalletTransaction transaction) {
        // Procesa la transacción actualizando el estado y los saldos de los monederos
        return walletTransactionService.save(transaction)
                .flatMap(this::updateWalletBalances)
                .then();
    }

    private Mono<WalletTransaction> updateWalletBalances(WalletTransaction transaction) {
        // TODO Actualizar el estado de la transacción como "SUCCESFULL" o "PENDING"
        return Mono.just(transaction);
    }
}
