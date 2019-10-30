package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Category;
import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.service.validation.rules.ProductDiscountValidationRule;
import com.javaguru.shoppinglist.service.validation.rules.ProductNameValidationRule;
import com.javaguru.shoppinglist.service.validation.rules.ProductPriceValidationRule;
import com.javaguru.shoppinglist.service.validation.rules.ProductValidationRule;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class ProductValidationServiceTest {

    @Mock
    private ProductPriceValidationRule priceRule;

    @Mock
    private ProductDiscountValidationRule discountRule;

    @Mock
    private ProductNameValidationRule nameRule;

    @Mock
    private ValidationRules rulesSet;

    @InjectMocks
    private ProductValidationService victim;

    @Captor
    private ArgumentCaptor<Product> captor;

    private Set<ProductValidationRule> validationRules;

    @Before
    public void prepareTest() {
        validationRules = new HashSet<>();
        validationRules.add(priceRule);
        validationRules.add(discountRule);
        validationRules.add(nameRule);
    }

    @Test
    public void shouldValidateProduct() {
        Product testProduct = createTestProduct();
        when(rulesSet.getValidationRules()).thenReturn(validationRules);
        victim.validate(testProduct);
        verify(priceRule).validate(captor.capture());
        verify(discountRule).validate(captor.capture());
        verify(nameRule).validate(captor.capture());

        List<Product> resultList = captor.getAllValues();
        assertThat(resultList).containsOnly(testProduct);
    }

    private Product createTestProduct() {
        Product product = new Product();
        product.setId(10L);
        product.setName("Test name");
        product.setDescription("Test description");
        product.setCategory(Category.FOOD);
        product.setPrice(new BigDecimal(70));
        product.setDiscount(new BigDecimal(20));
        return product;
    }
}