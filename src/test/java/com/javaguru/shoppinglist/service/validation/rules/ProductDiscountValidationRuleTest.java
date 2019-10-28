package com.javaguru.shoppinglist.service.validation.rules;

import com.javaguru.shoppinglist.domain.Product;

import java.math.BigDecimal;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ProductDiscountValidationRuleTest {

    @Spy
    @InjectMocks
    private ProductDiscountValidationRule victim;

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Test
    public void shouldNotThrowAnyValidationException() {
        Product testProduct = createTestProduct(50, 15);
        victim.validate(testProduct);
        verify(victim).checkNotNull(testProduct);
    }

    @Test
    public void shouldThrowDiscountValueTooBigException() {
        Product testProduct = createTestProduct(50, 120);
        expectedException.expect(ProductValidationException.class);
        expectedException.expectMessage("Product discount can't be more than 100%.");

        victim.validate(testProduct);
        verify(victim).checkNotNull(testProduct);
    }

    @Test
    public void shouldThrowDiscountValueIsNegativeException() {
        Product testProduct = createTestProduct(50, -1);
        expectedException.expect(ProductValidationException.class);
        expectedException.expectMessage("Product discount can't be less than 0%.");

        victim.validate(testProduct);
        verify(victim).checkNotNull(testProduct);
    }

    @Test
    public void shouldThrowNoDiscountForLowPriceProductException() {
        Product testProduct = createTestProduct(10, 50);
        expectedException.expect(ProductValidationException.class);
        expectedException.expectMessage("Product can't have discount if price is less than 20.");

        victim.validate(testProduct);
        verify(victim).checkNotNull(testProduct);
    }

    private Product createTestProduct(int price, int discount) {
        Product product = new Product();
        product.setPrice(BigDecimal.valueOf(price));
        product.setDiscount(BigDecimal.valueOf(discount));
        return product;
    }
}