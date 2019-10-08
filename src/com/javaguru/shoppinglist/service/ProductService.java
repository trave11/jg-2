package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.Category;
import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.repository.ProductRepository;
import com.javaguru.shoppinglist.service.validation.ProductValidationException;
import com.javaguru.shoppinglist.service.validation.ProductValidationService;

import java.math.BigDecimal;

public class ProductService {
    private final ProductRepository productRepository;
    private final ProductValidationService productValidationService;

    public ProductService(ProductRepository productRepository, ProductValidationService productValidationService) {
        this.productRepository = productRepository;
        this.productValidationService = productValidationService;
    }

    public Product createProduct(String name, String description, Category category, BigDecimal price, BigDecimal discount) {
        Product product = new Product();
        product.setId(productRepository.getProductIdSequence());
        product.setName(name);
        product.setCategory(category);
        product.setPrice(price);
        product.setDiscount(discount);
        product.setDescription(description);
        productValidationService.validate(product);
        productRepository.addProduct(product);
        return product;
    }

    public Product findProductById(Long id) {
        Product product = productRepository.getProduct(id);
        if (product == null) {
            throw new ProductValidationException("Product was not found!");
        }
        return product;
    }
}