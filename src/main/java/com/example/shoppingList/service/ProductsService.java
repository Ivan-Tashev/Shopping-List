package com.example.shoppingList.service;

import com.example.shoppingList.model.binding.ProductsAddBindModel;
import com.example.shoppingList.model.entity.enums.CategoryEnum;
import com.example.shoppingList.model.service.ProductServiceModel;

import java.util.List;

public interface ProductsService {
    boolean checkExistingName(String name);

    ProductServiceModel addProduct(ProductsAddBindModel productsAddBindModel);

    List<ProductServiceModel> findByCategory(CategoryEnum category);

    List<ProductServiceModel> findAll();

    void deleteById(Long id);

    void deleteAll();
}
