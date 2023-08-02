package com.microservices.project4.Yanki.exceptions;

public class TransactionFailedException extends RuntimeException{
    public TransactionFailedException(String message) {
        super(message);
    }
}
