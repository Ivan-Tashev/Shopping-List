package com.example.shoppingList.model.service;

import com.example.shoppingList.model.entity.CategoryEntity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ProductServiceModel {
    private Long id;
    private String name;
    private String description;
    private LocalDate neededBefore;
    private BigDecimal price;
    private CategoryEntity categoryEntity;

    public Long getId() {
        return id;
    }

    public ProductServiceModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProductServiceModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ProductServiceModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public LocalDate getNeededBefore() {
        return neededBefore;
    }

    public ProductServiceModel setNeededBefore(LocalDate neededBefore) {
        this.neededBefore = neededBefore;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ProductServiceModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public CategoryEntity getCategoryEntity() {
        return categoryEntity;
    }

    public ProductServiceModel setCategoryEntity(CategoryEntity categoryEntity) {
        this.categoryEntity = categoryEntity;
        return this;
    }
}
