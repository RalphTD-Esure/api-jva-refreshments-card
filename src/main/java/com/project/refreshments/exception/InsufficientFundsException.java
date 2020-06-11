package com.project.refreshments.exception;


public class InsufficientFundsException extends RuntimeException {

    public InsufficientFundsException() {
        super();
    }

    public InsufficientFundsException(String message) {
        super(message);
    }
}
