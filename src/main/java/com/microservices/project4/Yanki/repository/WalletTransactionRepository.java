package com.microservices.project4.Yanki.repository;

import com.microservices.project4.Yanki.model.WalletTransaction;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface WalletTransactionRepository extends ReactiveCrudRepository<WalletTransaction, Long> {
}
