package com.rusakov.statementAccounts.controller;

import com.rusakov.statementAccounts.entity.AccountEntity;
import com.rusakov.statementAccounts.entity.EndListEntity;
import com.rusakov.statementAccounts.entity.ProductEntity;
import com.rusakov.statementAccounts.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.math.BigDecimal;

@Controller
public class EndListController {

    private final EndListRepository endListRepository;
    private final ProductRepository productRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public EndListController(EndListRepository endListRepository, ProductRepository productRepository, AccountRepository accountRepository) {
        this.endListRepository = endListRepository;
        this.productRepository = productRepository;
        this.accountRepository = accountRepository;
    }

    @GetMapping("/end_list")
    public String getAllEndLists(Model model) {
        Iterable<EndListEntity> endLists = endListRepository.findAll();

        endLists.forEach(endListEntity -> {
            BigDecimal finalPrice = endListEntity.getProduct().getPrice().multiply(BigDecimal.valueOf(endListEntity.getOrderCount()));
            endListEntity.setEndPrice(finalPrice);
        });

        model.addAttribute("endLists", endLists);
        return "endList/endLists";
    }

    @GetMapping("/end_list/add")
    public String showAddEndListForm(Model model) {
        Iterable<ProductEntity> products = productRepository.findAll();
        Iterable<AccountEntity> accounts = accountRepository.findAll();
        model.addAttribute("products", products);
        model.addAttribute("accounts", accounts);
        model.addAttribute("endList", new EndListEntity());
        return "endList/endList-add";
    }

    @PostMapping("/end_list/add")
    public String addEndList(@ModelAttribute("endList") EndListEntity endList) {
        endListRepository.save(endList);
        return "redirect:/end_list";
    }

    @GetMapping("/end_list/edit/{id}")
    public String showEditEndListForm(@PathVariable("id") Integer id, Model model) {
        EndListEntity endList = endListRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid endList Id:" + id));
        Iterable<ProductEntity> products = productRepository.findAll();
        Iterable<AccountEntity> accounts = accountRepository.findAll();
        model.addAttribute("endList", endList);
        model.addAttribute("products", products);
        model.addAttribute("accounts", accounts);
        return "endList/endList-edit";
    }

    @PostMapping("/end_list/edit/{id}")
    public String editEndList(@PathVariable("id") Integer id, @ModelAttribute("endList") EndListEntity endList) {
        endList.setId(id);
        endListRepository.save(endList);
        return "redirect:/end_list";
    }

    @GetMapping("/end_list/delete/{id}")
    public String deleteEndList(@PathVariable("id") Integer id) {
        endListRepository.deleteById(id);
        return "redirect:/end_list";
    }

}
