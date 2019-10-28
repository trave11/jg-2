package com.javaguru.shoppinglist.service.validation.rules;

import com.javaguru.shoppinglist.domain.Product;

import java.math.BigDecimal;

public class ProductDiscountValidationRule implements ProductValidationRule {
    @Override
    public void validate(Product product) {
        checkNotNull(product);
        BigDecimal maxDiscount = new BigDecimal("100");
        BigDecimal minProductPriceForDiscount = new BigDecimal("20");

        if (product.getDiscount().compareTo(maxDiscount) > 0) {
            throw new ProductValidationException("Product discount can't be more than 100%.");
        }

        if (product.getDiscount().compareTo(BigDecimal.ZERO) < 0) {
            throw new ProductValidationException("Product discount can't be less than 0%.");
        }

        if (product.getPrice().compareTo(minProductPriceForDiscount) < 0 && product.getDiscount().compareTo(BigDecimal.ZERO) > 0) {
            throw new ProductValidationException("Product can't have discount if price is less than 20.");
        }
    }
}