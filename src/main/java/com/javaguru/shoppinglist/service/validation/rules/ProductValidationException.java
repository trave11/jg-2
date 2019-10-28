package com.javaguru.shoppinglist.service.validation.rules;

public class ProductValidationException extends RuntimeException {
    public ProductValidationException(String message) {
        super(message);
    }
}