package com.javaguru.shoppinglist.ui;

import com.javaguru.shoppinglist.domain.Cart;
import com.javaguru.shoppinglist.domain.Category;
import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.service.CartService;
import com.javaguru.shoppinglist.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Scanner;
import java.util.Set;

@Component
public class ConsoleUI {
    private final ProductService productService;
    private final CartService cartService;

    @Autowired
    public ConsoleUI(ProductService productService, CartService cartService) {
        this.productService = productService;
        this.cartService = cartService;
    }

    public void execute() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            try {
                System.out.println("1. Create product");
                System.out.println("2. Find product by id");
                System.out.println("3. Create new cart");
                System.out.println("4. Add product to cart");
                System.out.println("5. Remove cart");
                System.out.println("6. Display cart products");
                System.out.println("7. Display total cost of products in cart");
                System.out.println("8. Exit");
                Integer userInput = Integer.valueOf(scanner.nextLine());
                switch (userInput) {
                    case 1:
                        createProduct();
                        break;
                    case 2:
                        findProduct();
                        break;
                    case 3:
                        createCart();
                        break;
                    case 4:
                        addProductToCart();
                        break;
                    case 5:
                        removeCart();
                        break;
                    case 6:
                        displayCartProducts();
                        break;
                    case 7:
                        displayTotalSumOfProductsInCart();
                        break;
                    case 8:
                        return;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void displayTotalSumOfProductsInCart() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter cart ID:");
        Long cartId = scanner.nextLong();
        BigDecimal sum = cartService.getCartProductsTotalCost(cartId);
        System.out.println("Total sum in " + cartId + " cart is " + sum);
    }

    private void displayCartProducts() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter cart ID:");
        Long cartId = scanner.nextLong();
        Set<Product> cartProducts = cartService.getCartProducts(cartId);
        cartProducts.forEach(System.out::println);
    }

    private void removeCart() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter cart ID:");
        Long cartId = scanner.nextLong();
        cartService.removeCartById(cartId);
        System.out.println("The cart has been removed");
    }

    private void addProductToCart() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter product ID:");
        Long productId = scanner.nextLong();
        System.out.println("Enter cart ID:");
        Long cartId = scanner.nextLong();
        cartService.addProductToCart(productId, cartId);
        System.out.println("Product " + productId + " was added to the " + cartId + " cart");
    }

    private void createCart() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter cart name: ");
        String name = scanner.nextLine();
        Cart cart = cartService.createCart(name);
        System.out.println("New " + cart.getName() + " cart has been created.");
    }

    private void createProduct() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter product name: ");
        String name = scanner.nextLine();
        System.out.println("Enter product description: ");
        String description = scanner.nextLine();
        System.out.println("Enter product category (FOOD, TOOLS, TOYS, OTHER): ");
        Category category = Category.valueOf(scanner.nextLine());
        System.out.println("Enter product price: ");
        BigDecimal price = new BigDecimal(scanner.nextLine());
        System.out.println("Enter product discount: ");
        BigDecimal discount = new BigDecimal(scanner.nextLine());
        Product product = productService.createProduct(name, description, category, price, discount);
        System.out.println("Product has been successfully created: " + product.toString());
    }

    private void findProduct() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter product id: ");
        long id = scanner.nextLong();
        Product foundProduct = productService.findProductById(id);
        System.out.println(foundProduct);
    }
}