package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.repository.ProductRepository;
import com.javaguru.shoppinglist.service.validation.rules.ProductDiscountValidationRule;
import com.javaguru.shoppinglist.service.validation.rules.ProductNameValidationRule;
import com.javaguru.shoppinglist.service.validation.rules.ProductPriceValidationRule;
import com.javaguru.shoppinglist.service.validation.rules.ProductValidationRule;

import java.util.HashSet;
import java.util.Set;

public class ValidationRulesSet {
    private final Set<ProductValidationRule> validationRules = new HashSet<>();

    public ValidationRulesSet(ProductRepository productRepository) {
        validationRules.add(new ProductNameValidationRule(productRepository));
        validationRules.add(new ProductPriceValidationRule());
        validationRules.add(new ProductDiscountValidationRule());
    }

    Set<ProductValidationRule> getValidationRules() {
        return validationRules;
    }
}