package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.Cart;
import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.repository.CartRepositoryHibernate;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
public class CartService {
    private final CartRepositoryHibernate cartRepository;
    private final ProductService productService;

    @Autowired
    public CartService(CartRepositoryHibernate cartRepository, ProductService productService) {
        this.cartRepository = cartRepository;
        this.productService = productService;
    }

    @Transactional
    public Cart createCart(Cart cart) {
        Long cartId = cartRepository.save(cart);
        cart.setId(cartId);
        return cart;
    }

    @Transactional
    public void updateCart(Cart cart) {
        cartRepository.update(cart);
    }

    @Transactional
    public Cart findCartById(Long cartId) {
        return cartRepository.findCartById(cartId).orElseThrow(() ->
                new NoSuchElementException("The provided cart was not found!"));
    }

    @Transactional
    public void removeCartById(Long cartId) {
        Cart cart = findCartById(cartId);
        cartRepository.remove(cart);
    }

    @Transactional
    private Set<Product> getCartProducts(Long cartId) {
        return findCartById(cartId).getProducts();
    }

    @Transactional
    public BigDecimal getCartProductsTotalCost(Long cartId) {
        Set<Product> products = getCartProducts(cartId);
        return products.stream().map(Product::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Transactional
    public void addProductToCart(Long cartId, Long productId) {
        Cart cart = findCartById(cartId);
        Product product = productService.findProductById(productId);
        cart.getProducts().add(product);
        cartRepository.update(cart);
    }

    public List<Cart> getAllCarts() {
        return cartRepository.getAll();
    }
}