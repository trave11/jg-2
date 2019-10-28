package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProductValidationService {
    private final ValidationRulesSet validationRules;

    @Autowired
    public ProductValidationService(ValidationRulesSet validationRules) {
        this.validationRules = validationRules;
    }

    public void validate(Product product) {
        validationRules.getValidationRules().forEach(s -> s.validate(product));
    }
}