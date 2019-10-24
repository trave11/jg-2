package com.javaguru.shoppinglist.service.validation.rules;

import com.javaguru.shoppinglist.domain.Product;

public interface ProductValidationRule {
    void validate(Product product);

    default void checkNotNull(Product product) {
        if (product == null) {
            throw new ProductValidationException("Product must not be null.");
        }
    }
}