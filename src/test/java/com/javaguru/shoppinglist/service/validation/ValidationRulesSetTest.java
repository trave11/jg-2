package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.repository.ProductRepository;
import com.javaguru.shoppinglist.service.validation.rules.ProductDiscountValidationRule;
import com.javaguru.shoppinglist.service.validation.rules.ProductNameValidationRule;
import com.javaguru.shoppinglist.service.validation.rules.ProductPriceValidationRule;
import com.javaguru.shoppinglist.service.validation.rules.ProductValidationRule;

import org.junit.Test;
import org.mockito.Mock;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class ValidationRulesSetTest {

    @Mock
    private ProductRepository repository;

    private ValidationRulesSet victim = new ValidationRulesSet(repository);

    @Test
    public void shouldReturnValidationRulesSet() {
        Set<ProductValidationRule> rulesSet = victim.getValidationRules();
        assertThat(rulesSet).extracting("class").containsOnlyOnce(ProductNameValidationRule.class);
        assertThat(rulesSet).extracting("class").containsOnlyOnce(ProductPriceValidationRule.class);
        assertThat(rulesSet).extracting("class").containsOnlyOnce(ProductDiscountValidationRule.class);
    }
}