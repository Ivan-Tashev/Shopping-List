package com.example.shoppingList.service.impl;

import com.example.shoppingList.model.entity.CategoryEntity;
import com.example.shoppingList.model.entity.enums.CategoryEnum;
import com.example.shoppingList.repo.CategoryRepo;
import com.example.shoppingList.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepo categoryRepo;

    public CategoryServiceImpl(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @Override
    public void initCategories() {
        final CategoryEntity food = new CategoryEntity().setName(CategoryEnum.Food).setDescription("Food");
        final CategoryEntity drink = new CategoryEntity().setName(CategoryEnum.Drink).setDescription("Drink");
        final CategoryEntity household = new CategoryEntity().setName(CategoryEnum.Household).setDescription("Household");
        final CategoryEntity other = new CategoryEntity().setName(CategoryEnum.Other).setDescription("Other");
        categoryRepo.saveAll(List.of(food, drink, household, other));
    }

    @Override
    public CategoryEntity findByName(CategoryEnum category) {
        return categoryRepo.findByName(category);
    }
}
