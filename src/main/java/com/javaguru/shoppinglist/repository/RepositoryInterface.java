package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.domain.Product;

import java.util.Optional;

public interface RepositoryInterface {
    Optional<Product> findProduct(Long productId);

    Long save(Product product);

    boolean existsByName(String providedName);
}