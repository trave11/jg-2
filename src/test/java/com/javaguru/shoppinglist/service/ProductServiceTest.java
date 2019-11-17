package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.Category;
import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.repository.RepositoryInterface;
import com.javaguru.shoppinglist.service.validation.ProductValidationService;
import com.javaguru.shoppinglist.service.validation.rules.ProductValidationException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @Mock
    private RepositoryInterface repository;

    @Mock
    private ProductValidationService validationService;

    @InjectMocks
    private ProductService victim;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Captor
    private ArgumentCaptor<Product> productCaptor;

    @Test
    public void shouldFindProduct() {
        when(repository.findProduct(10L)).thenReturn(Optional.of(createTestProduct()));
        Product actualResult = victim.findProductById(10L);
        Product expectedResult = createTestProduct();
        assertThat(expectedResult).isEqualTo(actualResult);
    }

    @Test
    public void shouldNotFindProduct() {
        expectedException.expect(ProductValidationException.class);
        expectedException.expectMessage("Product was not found!");
        Product actualResult = victim.findProductById(10L);
        Product expectedResult = null;
        assertThat(expectedResult).isEqualTo(actualResult);
    }

    @Test
    public void shouldCreateProduct() {

        when(repository.save(any(Product.class))).thenReturn(10L);
        Product actualResult = victim.createProduct("Test name", "Test description", Category.FOOD, new BigDecimal(70), new BigDecimal(20));
        Product expectedResult = createTestProduct();

        verify(validationService).validate(productCaptor.capture());
        verify(repository).save(productCaptor.capture());
        List<Product> captorResult = productCaptor.getAllValues();

        assertThat(captorResult).containsOnly(expectedResult);
        assertThat(expectedResult).isEqualTo(actualResult);
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