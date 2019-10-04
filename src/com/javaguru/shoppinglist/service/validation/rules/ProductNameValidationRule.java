package com.javaguru.shoppinglist.service.validation.rules;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.repository.ProductRepository;
import com.javaguru.shoppinglist.service.validation.ProductValidationException;

public class ProductNameValidationRule implements ProductValidationRule {
    private final ProductRepository productRepository;

    public ProductNameValidationRule(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void validate(Product product) {
        checkNotNull(product);
        if (product.getName().length() < 3) {
            throw new ProductValidationException("Product name length must not be smaller than 3.");
        }

        if (product.getName().length() > 32) {
            throw new ProductValidationException("Product name length must not be larger than 32.");
        }

        if (productRepository.existsByName(product.getName())) {
            throw new ProductValidationException("Product name must be unique.");
        }
    }
}