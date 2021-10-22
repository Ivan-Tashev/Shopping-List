package com.example.shoppingList.web;

import com.example.shoppingList.model.entity.enums.CategoryEnum;
import com.example.shoppingList.model.service.ProductServiceModel;
import com.example.shoppingList.model.view.ProductViewModel;
import com.example.shoppingList.service.ProductsService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {
    private final ProductsService productsService;
    private final ModelMapper modelMapper;

    public HomeController(ProductsService productsService, ModelMapper modelMapper) {
        this.productsService = productsService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/")
    public String getIndexPage() {
        return "index";
    }

    @GetMapping("/home")
    public String getHomePage(Model model) {
        Arrays.stream(CategoryEnum.values()).forEach(category -> {
            List<ProductServiceModel> byCategory = productsService.findByCategory(category);
            model.addAttribute(category.name(), byCategory.stream()
                    .map(productServiceModel -> modelMapper.map(productServiceModel, ProductViewModel.class))
                    .collect(Collectors.toList()));
        });

        model.addAttribute("sum", productsService.findAll().stream()
                .mapToDouble(value -> value.getPrice().doubleValue())
                .sum());
        return "home";
    }
}
