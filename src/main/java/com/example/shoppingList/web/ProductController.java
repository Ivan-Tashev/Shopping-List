package com.example.shoppingList.web;

import com.example.shoppingList.model.binding.ProductsAddBindModel;
import com.example.shoppingList.service.ProductsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class ProductController {
    private final ProductsService productsService;

    public ProductController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping("/products/add")
    public String getAddProductPage(Model model) {
        if (!model.containsAttribute("productsAddBindModel")) {
            model.addAttribute("productsAddBindModel", new ProductsAddBindModel())
                    .addAttribute("existingName", false);
        }
        return "product-add";
    }

    @PostMapping("/products/add")
    public String addProducts(@Valid ProductsAddBindModel productsAddBindModel,
                              BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        boolean checkExistingName = productsService.checkExistingName(productsAddBindModel.getName());
        if (bindingResult.hasErrors() || checkExistingName) {
            redirectAttributes.addFlashAttribute("productsAddBindModel", productsAddBindModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.productsAddBindModel", bindingResult);
            if (checkExistingName) {
                redirectAttributes.addFlashAttribute("existingName", true);
            }
            return "redirect:/products/add";
        }
        // save product to DB
        productsService.addProduct(productsAddBindModel);
        return "redirect:/home";
    }

    @DeleteMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productsService.deleteById(id);
        return "redirect:/home";
    }

    // Just for test purposes
    // TODO: fix the HTML to <form>'s with method="delete"
    @GetMapping("/products/delete/{id}")
    public String deleteProductFromLink(@PathVariable Long id) {
        productsService.deleteById(id);
        return "redirect:/home";
    }

    @GetMapping("/products/delete/all")
    public String deleteAllProducts() {
        productsService.deleteAll();
        return "redirect:/home";
    }

}
