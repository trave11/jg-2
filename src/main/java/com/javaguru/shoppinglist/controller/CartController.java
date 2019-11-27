package com.javaguru.shoppinglist.controller;

import com.javaguru.shoppinglist.domain.Cart;
import com.javaguru.shoppinglist.dto.CartDto;
import com.javaguru.shoppinglist.service.CartService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/carts")
public class CartController {

    private CartService cartService;
    private ModelMapper modelMapper;

    @Autowired
    public CartController(CartService cartService, ModelMapper modelMapper) {
        this.cartService = cartService;
        this.modelMapper = modelMapper;
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception exception) {
        return exception.getMessage();
    }

    @GetMapping("/{id}")
    public CartDto findCart(@PathVariable("id") Long cartId) {
        return modelMapper.map(cartService.findCartById(cartId), CartDto.class);
    }

    @GetMapping
    public List<CartDto> getAllCarts() {
        List<CartDto> cartDtos = new ArrayList<>();
        List<Cart> carts = cartService.getAllCarts();
        carts.forEach(cart -> cartDtos.add(modelMapper.map(cart, CartDto.class)));
        return cartDtos;
    }

    @GetMapping("/{id}/total-cost")
    public BigDecimal getCartProductsTotalCost(@PathVariable("id") Long id) {
        return cartService.getCartProductsTotalCost(id);
    }


    @PostMapping
    public CartDto createCart(@RequestBody CartDto cartDto) {
        Cart cart = cartService.createCart(modelMapper.map(cartDto, Cart.class));
        return modelMapper.map(cart, CartDto.class);
    }

    @PutMapping
    public void updateCart(@RequestBody CartDto cartDto) {
        cartService.updateCart(modelMapper.map(cartDto, Cart.class));
    }

    @PutMapping("/{cartId}")
    public void addProductToCart(@PathVariable("cartId") Long cartId, @RequestHeader("productId") Long productId) {
        cartService.addProductToCart(cartId, productId);
    }

    @DeleteMapping("/{cartId}")
    public void removeCart(@PathVariable("id") Long id) {
        cartService.removeCartById(id);
    }
}