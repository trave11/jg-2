package com.javaguru.shoppinglist.service.validation.rules;

import com.javaguru.shoppinglist.domain.Product;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ProductPriceValidationRule implements ProductValidationRule {
    @Override
    public void validate(Product product) {
        checkNotNull(product);

        if (product.getPrice().compareTo(BigDecimal.ZERO) < 1) {
            throw new ProductValidationException("Product price must be greater than 0.");
        }
    }
}