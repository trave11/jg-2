package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.Category;
import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.repository.RepositoryInterface;
import com.javaguru.shoppinglist.service.validation.rules.ProductValidationException;
import com.javaguru.shoppinglist.service.validation.ProductValidationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class ProductService {
    private final RepositoryInterface productRepository;
    private final ProductValidationService productValidationService;

    @Autowired
    public ProductService(RepositoryInterface productRepository, ProductValidationService productValidationService) {
        this.productRepository = productRepository;
        this.productValidationService = productValidationService;
    }

    @Transactional
    public Product createProduct(String name, String description, Category category, BigDecimal price, BigDecimal discount) {
        Product product = new Product();
        product.setName(name);
        product.setCategory(category);
        product.setPrice(price);
        product.setDiscount(discount);
        product.setDescription(description);
        productValidationService.validate(product);
        Long productId = productRepository.addProduct(product);
        product.setId(productId);
        return product;
    }

    public Product findProductById(Long id) {
        return productRepository.getProduct(id).orElseThrow(() ->
                new ProductValidationException("Product was not found!"));
    }
}