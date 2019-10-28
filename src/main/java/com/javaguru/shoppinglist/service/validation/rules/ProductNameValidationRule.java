package com.javaguru.shoppinglist.service.validation.rules;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductNameValidationRule implements ProductValidationRule {
    private final ProductRepository productRepository;

    @Autowired
    public ProductNameValidationRule(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void validate(Product product) {
        checkNotNull(product);
        int minNameLength = 3;
        int maxNameLength = 32;

        if (product.getName().length() < minNameLength) {
            throw new ProductValidationException("Product name length must not be smaller than 3.");
        }

        if (product.getName().length() > maxNameLength) {
            throw new ProductValidationException("Product name length must not be larger than 32.");
        }

        if (productRepository.existsByName(product.getName())) {
            throw new ProductValidationException("Product name must be unique.");
        }
    }
}