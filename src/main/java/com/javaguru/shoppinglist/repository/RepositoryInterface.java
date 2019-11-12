package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.domain.Product;

import java.util.Optional;

public interface RepositoryInterface {
    Optional<Product> getProduct(Long productId);

    Long addProduct(Product product);

    boolean existsByName(String providedName);
}