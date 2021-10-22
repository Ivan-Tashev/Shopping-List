package com.example.shoppingList.model.binding;

import com.example.shoppingList.model.entity.enums.CategoryEnum;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ProductsAddBindModel {
    @NotEmpty
    @Size(min = 3, max = 20)
    private String name;
    @Size(min = 5)
    private String description;
    @NotNull
    @Positive
    private BigDecimal price;
    @NotNull
    @FutureOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime neededBefore;
    @NotNull
    private CategoryEnum category;

    public String getName() {
        return name;
    }

    public ProductsAddBindModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ProductsAddBindModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public LocalDateTime getNeededBefore() {
        return neededBefore;
    }

    public ProductsAddBindModel setNeededBefore(LocalDateTime neededBefore) {
        this.neededBefore = neededBefore;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ProductsAddBindModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public ProductsAddBindModel setCategory(CategoryEnum category) {
        this.category = category;
        return this;
    }
}
