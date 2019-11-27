package com.javaguru.shoppinglist.dto;

import com.javaguru.shoppinglist.domain.Product;

import java.util.Set;

public class CartDto {

    private Long id;
    private String name;
    private Set<Product> products;

    CartDto() {
    }

    public CartDto(Long id, String name, Set<Product> products) {
        this.id = id;
        this.name = name;
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
