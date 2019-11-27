package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.domain.Product;

import java.util.List;
import java.util.Optional;

public interface RepositoryInterface {
    Optional<Product> findProduct(Long productId);

    Long save(Product product);

    boolean existsByName(String providedName);

    void delete(Product product);

    void update(Product product);

    List<Product> getAll();
}