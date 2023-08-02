package com.microservices.project4.Yanki.repository;

import com.microservices.project4.Yanki.model.Wallet;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface WalletRepository extends ReactiveCrudRepository<Wallet, Long> {
    Mono<Wallet> findByPhoneNumber(String phoneNumber);
}
