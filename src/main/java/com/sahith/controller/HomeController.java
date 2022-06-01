package com.sahith.controller;


import com.sahith.entity.Category;
import com.sahith.entity.Customer;
import com.sahith.entity.Item;
import com.sahith.services.interfaces.CategoryService;
import com.sahith.services.interfaces.CustomerService;
import com.sahith.services.interfaces.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private CustomerService customerService;

    @GetMapping("/home")
    public String home(Model model) {
        String curUser = customerService.getCurrentUserEmail();
        Customer customer = customerService.findCustomerByEmail(curUser);
        String curName = customer.getFirstName();
        model.addAttribute("curName",curName);
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        List<Item> itemList = itemService.findAll();
        model.addAttribute("items", itemList);
        model.addAttribute("categoryId", null);
        return "category-items";
    }

    @PostMapping("/home")
    public String getHome(Model model) {
        return home(model);
    }

}