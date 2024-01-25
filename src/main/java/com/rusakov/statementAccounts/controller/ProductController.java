package com.rusakov.statementAccounts.controller;

import com.rusakov.statementAccounts.entity.ProductEntity;
import com.rusakov.statementAccounts.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/products")
    public String products(Model model) {

        Iterable<ProductEntity> products = productRepository.findAll();

        model.addAttribute("products", products);

        return "product/products";
    }

    @GetMapping("/products/add")
    public String showAddForm(Model model) {
        model.addAttribute("product", new ProductEntity());
        return "product/product-add";
    }

    @PostMapping("/products/add")
    public String addProduct(@ModelAttribute("product") ProductEntity product) {
        productRepository.save(product);
        return "redirect:/products";
    }

    @GetMapping("/products/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model) {
        ProductEntity product = productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));
        model.addAttribute("product", product);
        return "product/product-edit";
    }

    @PostMapping("/products/edit/{id}")
    public String saveProduct(@PathVariable("id") Long id, @ModelAttribute("product") ProductEntity product) {
        productRepository.save(product);
        return "redirect:/products";
    }

    @GetMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable("id") Integer id) {
        productRepository.deleteById(id);
        return "redirect:/products";
    }

}
