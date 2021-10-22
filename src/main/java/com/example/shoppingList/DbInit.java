package com.example.shoppingList;

import com.example.shoppingList.repo.CategoryRepo;
import com.example.shoppingList.service.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DbInit implements CommandLineRunner {
    private final CategoryService categoryService;
    private final CategoryRepo categoryRepo;


    public DbInit(CategoryService categoryService, CategoryRepo categoryRepo) {
        this.categoryService = categoryService;
        this.categoryRepo = categoryRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        if (categoryRepo.count() == 0) {
            categoryService.initCategories();
        }
    }
}
