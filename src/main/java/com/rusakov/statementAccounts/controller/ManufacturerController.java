package com.rusakov.statementAccounts.controller;

import com.rusakov.statementAccounts.entity.ManufacturerEntity;
import com.rusakov.statementAccounts.repository.ManufacturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ManufacturerController {

    @Autowired
    private ManufacturerRepository manufacturerRepository;

    @GetMapping("/manufacturers")
    public String manufacturers(Model model) {

        Iterable<ManufacturerEntity> manufacturers = manufacturerRepository.findAll();

        model.addAttribute("manufacturers", manufacturers);

        return "manufacturer/manufacturers";
    }

    @GetMapping("/manufacturers/add")
    public String showAddForm(Model model) {
        model.addAttribute("manufacturer", new ManufacturerEntity());
        return "manufacturer/manufacturer-add";
    }

    @PostMapping("/manufacturers/add")
    public String saveManufacturer(@ModelAttribute("manufacturer") ManufacturerEntity manufacturer) {
        manufacturerRepository.save(manufacturer);
        return "redirect:/manufacturers";
    }

    @GetMapping("/manufacturers/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model) {
        ManufacturerEntity manufacturer = manufacturerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid manufacturer Id:" + id));
        model.addAttribute("manufacturer", manufacturer);
        return "manufacturer/manufacturer-edit";
    }

    @PostMapping("/manufacturers/edit/{id}")
    public String saveManufacturer(@PathVariable("id") Long id, @ModelAttribute("manufacturer") ManufacturerEntity manufacturer) {
        manufacturerRepository.save(manufacturer);
        return "redirect:/manufacturers";
    }

    @GetMapping("/manufacturers/delete/{id}")
    public String deleteManufacturer(@PathVariable("id") Integer id) {
        manufacturerRepository.deleteById(id);
        return "redirect:/manufacturers";
    }

}
