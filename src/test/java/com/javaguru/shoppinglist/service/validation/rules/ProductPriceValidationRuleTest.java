package com.javaguru.shoppinglist.service.validation.rules;

import com.javaguru.shoppinglist.domain.Product;

import java.math.BigDecimal;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.rules.ExpectedException;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ProductPriceValidationRuleTest {

    @Spy
    @InjectMocks
    private ProductPriceValidationRule victim;

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Test
    public void shouldThrowPriceValidationException() {
        Product testProduct = createTestProduct(0);
        expectedException.expect(ProductValidationException.class);
        expectedException.expectMessage("Product price must be greater than 0.");

        victim.validate(testProduct);
        verify(victim).checkNotNull(testProduct);
    }

    @Test
    public void shouldNotThrowPriceValidationException() {
        Product testProduct = createTestProduct(1);
        victim.validate(testProduct);
        verify(victim).checkNotNull(testProduct);
    }

    private Product createTestProduct(int price) {
        Product product = new Product();
        product.setPrice(BigDecimal.valueOf(price));
        return product;
    }
}