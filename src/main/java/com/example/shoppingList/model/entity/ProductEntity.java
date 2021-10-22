package com.example.shoppingList.model.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "products")
public class ProductEntity extends BaseEntity{
    @Column(nullable = false, unique = true, length = 20)
    private String name;
    @Column
    private String description;
    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate neededBefore;
    @Column
    private BigDecimal price;
    @ManyToOne
    private CategoryEntity categoryEntity;

    public String getName() {
        return name;
    }

    public ProductEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ProductEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public LocalDate getNeededBefore() {
        return neededBefore;
    }

    public ProductEntity setNeededBefore(LocalDate neededBefore) {
        this.neededBefore = neededBefore;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ProductEntity setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public CategoryEntity getCategoryEntity() {
        return categoryEntity;
    }

    public ProductEntity setCategoryEntity(CategoryEntity categoryEntity) {
        this.categoryEntity = categoryEntity;
        return this;
    }

    public CategoryEntity getCategory() {
        return categoryEntity;
    }

    public ProductEntity setCategory(CategoryEntity categoryEntity) {
        this.categoryEntity = categoryEntity;
        return this;
    }
}
