package com.microservices.project4.Yanki.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "wallet-yanki")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Wallet {
    private Long id;
    private Long idClient;
    private Double balance;
    private String phoneNumber;
}
