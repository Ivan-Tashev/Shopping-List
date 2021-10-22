package com.example.shoppingList.service;

import com.example.shoppingList.model.entity.CategoryEntity;
import com.example.shoppingList.model.entity.enums.CategoryEnum;

public interface CategoryService {
    void initCategories();

    CategoryEntity findByName(CategoryEnum category);
}
