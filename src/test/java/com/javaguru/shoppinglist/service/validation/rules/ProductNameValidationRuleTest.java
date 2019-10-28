package com.javaguru.shoppinglist.service.validation.rules;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.repository.ProductRepository;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ProductNameValidationRuleTest {

    @Mock
    private ProductRepository repository;

    @Spy
    @InjectMocks
    private ProductNameValidationRule victim;

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Captor
    private ArgumentCaptor<Product> productCaptor;

    @Test
    public void shouldThrowMinLengthValidationException() {
        Product testProduct = product("ab");
        expectedException.expect(ProductValidationException.class);
        expectedException.expectMessage("Product name length must not be smaller than 3.");

        victim.validate(testProduct);

        verify(victim).checkNotNull(testProduct);
    }

    @Test
    public void shouldThrowMaxLengthValidationException() {
        Product testProduct = product("Lorem ipsum dolor sit amet orci aliquam.");
        expectedException.expect(ProductValidationException.class);
        expectedException.expectMessage("Product name length must not be larger than 32.");
        victim.validate(testProduct);

        verify(victim).checkNotNull(testProduct);
    }

    @Test
    public void shouldThrowNotUniqueNameValidationException() {
        Product testProduct = product("Apple");
        when(repository.existsByName(testProduct.getName())).thenReturn(true);
        expectedException.expect(ProductValidationException.class);
        expectedException.expectMessage("Product name must be unique.");

        victim.validate(testProduct);
        verify(victim).checkNotNull(productCaptor.capture());
        verify(repository).existsByName(testProduct.getName());

        Product captorResult = productCaptor.getValue();
        assertEquals(captorResult, testProduct);
    }

    @Test
    public void shouldNotThrowAnyValidationException() {
        Product testProduct = product("Apple");
        when(repository.existsByName(testProduct.getName())).thenReturn(false);

        victim.validate(testProduct);
        verify(victim).checkNotNull(testProduct);
        verify(repository).existsByName(testProduct.getName());
    }

    private Product product(String name) {
        Product product = new Product();
        product.setName(name);
        return product;
    }
}