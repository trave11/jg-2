package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.repository.RepositoryInterface;
import com.javaguru.shoppinglist.service.validation.rules.ProductDiscountValidationRule;
import com.javaguru.shoppinglist.service.validation.rules.ProductNameValidationRule;
import com.javaguru.shoppinglist.service.validation.rules.ProductPriceValidationRule;
import com.javaguru.shoppinglist.service.validation.rules.ProductValidationRule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
class ValidationRules {
    private final Set<ProductValidationRule> validationRules = new HashSet<>();

    @Autowired
    ValidationRules(RepositoryInterface productRepository) {
        validationRules.add(new ProductNameValidationRule(productRepository));
        validationRules.add(new ProductPriceValidationRule());
        validationRules.add(new ProductDiscountValidationRule());
    }

    Set<ProductValidationRule> getValidationRules() {
        return validationRules;
    }
}