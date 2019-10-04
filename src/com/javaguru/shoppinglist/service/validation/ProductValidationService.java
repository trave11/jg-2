package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;

public class ProductValidationService {
    private final ValidationRulesSet validationRules;

    public ProductValidationService(ValidationRulesSet validationRules) {
        this.validationRules = validationRules;
    }

    public void validate(Product product) {
        validationRules.getValidationRules().forEach(s -> s.validate(product));
    }
}