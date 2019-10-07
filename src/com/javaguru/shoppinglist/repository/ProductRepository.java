package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.domain.Product;

import java.util.HashMap;
import java.util.Map;

public class ProductRepository {
    private final Map<Long, Product> productRepository = new HashMap<>();
    private Long productIdSequence = 0L;

    public void addProduct(Product product) {
        productRepository.put(productIdSequence, product);
        productIdSequence++;
    }

    public Product getProduct(Long productId) {
        return productRepository.get(productId);
    }

    public Long getProductIdSequence() {
        return productIdSequence;
    }

    public boolean existsByName(String providedName) {
        for (Product product : productRepository.values()) {
            if (product.getName().equals(providedName)) {
                return true;
            }
        }
        return false;
    }
}