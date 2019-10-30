package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProductValidationService {
    private final ValidationRules validationRules;

    @Autowired
    public ProductValidationService(ValidationRules validationRules) {
        this.validationRules = validationRules;
    }

    public void validate(Product product) {
        validationRules.getValidationRules().forEach(s -> s.validate(product));
    }
}