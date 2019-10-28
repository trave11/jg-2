package com.javaguru.shoppinglist.service.validation.rules;

import com.javaguru.shoppinglist.domain.Product;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@RunWith(MockitoJUnitRunner.class)
public class ProductValidationRuleTest {

    @Spy
    private ProductValidationRule victim;

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Test
    public void shouldThrowProductIsNullException() {
        Product testProduct = null;
        assertThatThrownBy(() -> victim.checkNotNull(testProduct))
                .isInstanceOf(ProductValidationException.class)
                .hasMessage("Product must not be null.");
    }

    @Test
    public void shouldNotThrowAnyException() {
        Product testProduct = createTestProduct();
        victim.checkNotNull(testProduct);
    }

    private Product createTestProduct() {
        return new Product();
    }
}