package com.rusakov.statementAccounts.controller;

import com.rusakov.statementAccounts.entity.ManufacturerEntity;
import com.rusakov.statementAccounts.entity.ProductEntity;
import com.rusakov.statementAccounts.entity.SupplierEntity;
import com.rusakov.statementAccounts.repository.ManufacturerRepository;
import com.rusakov.statementAccounts.repository.ProductRepository;
import com.rusakov.statementAccounts.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class SupplierController {

    private final SupplierRepository supplierRepository;
    private final ProductRepository productRepository;
    private final ManufacturerRepository manufacturerRepository;

    @Autowired
    public SupplierController(SupplierRepository supplierRepository, ProductRepository productRepository, ManufacturerRepository manufacturerRepository) {
        this.supplierRepository = supplierRepository;
        this.productRepository = productRepository;
        this.manufacturerRepository = manufacturerRepository;
    }

    @GetMapping("/suppliers")
    public String getAllSuppliers(Model model) {
        Iterable<SupplierEntity> suppliers = supplierRepository.findAll();
        model.addAttribute("suppliers", suppliers);
        return "supplier/suppliers";
    }

    @GetMapping("/suppliers/add")
    public String showAddSupplierForm(Model model) {
        Iterable<ProductEntity> products = productRepository.findAll();
        Iterable<ManufacturerEntity> manufacturers = manufacturerRepository.findAll();
        model.addAttribute("products", products);
        model.addAttribute("manufacturers", manufacturers);
        return "supplier/supplier-add";
    }

    @PostMapping("/suppliers/add")
    public String addSupplier(@ModelAttribute("supplier") SupplierEntity supplier) {
        supplierRepository.save(supplier);
        return "redirect:/suppliers";
    }

    @GetMapping("/suppliers/edit/{id}")
    public String showEditSupplierForm(@PathVariable("id") Integer id, Model model) {
        SupplierEntity supplier = supplierRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid supplier Id:" + id));
        Iterable<ProductEntity> products = productRepository.findAll();
        Iterable<ManufacturerEntity> manufacturers = manufacturerRepository.findAll();
        model.addAttribute("supplier", supplier);
        model.addAttribute("products", products);
        model.addAttribute("manufacturers", manufacturers);
        return "supplier/supplier-edit";
    }

    @PostMapping("/suppliers/edit/{id}")
    public String editSupplier(@PathVariable("id") Integer id, @ModelAttribute("supplier") SupplierEntity supplier) {
        supplier.setId(id);
        supplierRepository.save(supplier);
        return "redirect:/suppliers";
    }

    @GetMapping("/suppliers/delete/{id}")
    public String deleteSupplier(@PathVariable("id") Integer id) {
        supplierRepository.deleteById(id);
        return "redirect:/suppliers";
    }
}