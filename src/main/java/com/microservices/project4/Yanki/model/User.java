package com.microservices.project4.Yanki.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user-yanki")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String dni;
    private String phoneNumber;
    private String imei;
    private String email;
}
