package com.rusakov.statementAccounts.controller;

import com.rusakov.statementAccounts.entity.AccountEntity;
import com.rusakov.statementAccounts.entity.UserEntity;
import com.rusakov.statementAccounts.repository.AccountRepository;
import com.rusakov.statementAccounts.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AccountController {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    @Autowired
    public AccountController(AccountRepository accountRepository, UserRepository userRepository) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/accounts")
    public String accounts(Model model) {
        Iterable<AccountEntity> accounts = accountRepository.findAll();
        model.addAttribute("accounts", accounts);
        return "account/accounts";
    }

    @GetMapping("/accounts/add")
    public String showAddAccountForm(Model model) {
        Iterable<UserEntity> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "account/account-add";
    }

    @PostMapping("/accounts/add")
    public String saveAccount(@ModelAttribute("account") AccountEntity account) {
        accountRepository.save(account);
        return "redirect:/accounts";
    }

    @GetMapping("/accounts/edit/{id}")
    public String showEditAccountForm(@PathVariable("id") Integer id, Model model) {
        AccountEntity account = accountRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid account Id:" + id));
        Iterable<UserEntity> users = userRepository.findAll();
        model.addAttribute("account", account);
        model.addAttribute("users", users);
        return "account/account-edit";
    }

    @PostMapping("/accounts/edit/{id}")
    public String editAccount(@PathVariable("id") Integer id, @ModelAttribute("account") AccountEntity account) {
        account.setId(id);
        accountRepository.save(account);
        return "redirect:/accounts";
    }

    @GetMapping("/accounts/delete/{id}")
    public String deleteAccount(@PathVariable("id") Integer id) {
        accountRepository.deleteById(id);
        return "redirect:/accounts";
    }
}