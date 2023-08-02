package com.microservices.project4.Yanki.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "transactions-yanki")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WalletTransaction {
    private Long id;
    private String senderPhoneNumber;
    private String receiverPhoneNumber;
    private double amount;
    private String transactionStatus;
}
