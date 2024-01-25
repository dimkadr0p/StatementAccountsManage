package com.rusakov.statementAccounts.controller;

import com.rusakov.statementAccounts.entity.UserEntity;
import com.rusakov.statementAccounts.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public String users(Model model) {

        Iterable<UserEntity> users = userRepository.findAll();

        model.addAttribute("users", users);

        return "user/users";
    }

    @GetMapping("/users/add")
    public String showAddForm(Model model) {
        model.addAttribute("user", new UserEntity());
        return "user/user-add";
    }

    @PostMapping("/users/add")
    public String saveUser(@ModelAttribute("user") UserEntity user) {
        userRepository.save(user);
        return "redirect:/users";
    }

    @GetMapping("/users/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model) {
        UserEntity user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("user", user);
        return "user/user-edit";
    }

    @PostMapping("/users/edit/{id}")
    public String saveUser(@PathVariable("id") Long id, @ModelAttribute("user") UserEntity user) {
        userRepository.save(user);
        return "redirect:/users";
    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id) {
        userRepository.deleteById(id);
        return "redirect:/users";
    }

}
