package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.repository.RepositoryInterface;
import com.javaguru.shoppinglist.service.validation.rules.ProductValidationException;
import com.javaguru.shoppinglist.service.validation.ProductValidationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


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
    public Product createProduct(Product product) {
        productValidationService.validate(product);
        Long productId = productRepository.save(product);
        product.setId(productId);
        return product;
    }

    @Transactional
    public Product findProductById(Long id) {
        return productRepository.findProduct(id).orElseThrow(() ->
                new ProductValidationException("Product was not found!"));
    }

    public void deleteProductByID(Long id) {
        Product product = findProductById(id);
        productRepository.delete(product);
    }

    @Transactional
    public void updateProduct(Product updatedProduct) {
        productValidationService.validate(updatedProduct);
        productRepository.update(updatedProduct);
    }

    @Transactional
    public List<Product> getAllProducts() {
        return productRepository.getAll();
    }
}