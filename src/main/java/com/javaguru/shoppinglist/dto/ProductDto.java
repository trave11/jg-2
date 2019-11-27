package com.javaguru.shoppinglist.dto;

import com.javaguru.shoppinglist.domain.Category;

import java.math.BigDecimal;

public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private Category category;
    private BigDecimal price;
    private BigDecimal discount;
    private Long cartId;

    ProductDto() {
    }

    public ProductDto(Long id, String name, String description, Category category, BigDecimal price, BigDecimal discount, Long cartId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.price = price;
        this.discount = discount;
        this.cartId = cartId;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }
}
