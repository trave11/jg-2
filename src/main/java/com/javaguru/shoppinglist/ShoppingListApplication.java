package com.javaguru.shoppinglist;

import com.javaguru.shoppinglist.service.ProductService;
import com.javaguru.shoppinglist.service.validation.ProductValidationService;
import com.javaguru.shoppinglist.service.validation.ValidationRulesSet;
import com.javaguru.shoppinglist.repository.ProductRepository;
import com.javaguru.shoppinglist.ui.ConsoleUI;

class ShoppingListApplication {

    public static void main(String[] args) {
        ProductRepository productRepository = new ProductRepository();
        ValidationRulesSet validationRulesSet = new ValidationRulesSet(productRepository);
        ProductValidationService productValidationService = new ProductValidationService(validationRulesSet);
        ProductService productService = new ProductService(productRepository, productValidationService);
        ConsoleUI consoleUI = new ConsoleUI(productService);
        consoleUI.execute();
    }
}