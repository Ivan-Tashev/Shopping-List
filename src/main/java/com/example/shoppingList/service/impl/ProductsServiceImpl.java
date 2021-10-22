package com.example.shoppingList.service.impl;

import com.example.shoppingList.model.binding.ProductsAddBindModel;
import com.example.shoppingList.model.entity.ProductEntity;
import com.example.shoppingList.model.entity.enums.CategoryEnum;
import com.example.shoppingList.model.service.ProductServiceModel;
import com.example.shoppingList.repo.ProductRepo;
import com.example.shoppingList.service.CategoryService;
import com.example.shoppingList.service.ProductsService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductsServiceImpl implements ProductsService {
    private final ProductRepo productRepo;
    private final ModelMapper modelMapper;
    private final CategoryService categoryService;

    public ProductsServiceImpl(ProductRepo productRepo, ModelMapper modelMapper, CategoryService categoryService) {
        this.productRepo = productRepo;
        this.modelMapper = modelMapper;
        this.categoryService = categoryService;
    }

    @Override
    public boolean checkExistingName(String name) {
        return productRepo.findByName(name).orElse(null) != null;
    }

    @Override
    public ProductServiceModel addProduct(ProductsAddBindModel productsAddBindModel) {
        final ProductServiceModel productServiceModel = modelMapper.map(productsAddBindModel, ProductServiceModel.class)
                .setNeededBefore(productsAddBindModel.getNeededBefore().toLocalDate())
                .setCategoryEntity(categoryService.findByName(productsAddBindModel.getCategory()));

        final ProductEntity savedProductEntity = productRepo.save(modelMapper.map(productServiceModel, ProductEntity.class));
        return modelMapper.map(savedProductEntity, ProductServiceModel.class);
    }

    @Override
    public List<ProductServiceModel> findByCategory(CategoryEnum category) {
        return productRepo.findAllByCategoryName(category).stream()
                .map(productEntity -> modelMapper.map(productEntity, ProductServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductServiceModel> findAll() {
        return productRepo.findAll().stream()
                .map(productEntity -> modelMapper.map(productEntity, ProductServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        productRepo.deleteById(id);
    }

    @Override
    public void deleteAll() {
        productRepo.deleteAll();
    }
}
