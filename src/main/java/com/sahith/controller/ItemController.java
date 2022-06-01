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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private CustomerService customerService;


    @Autowired
    private CategoryService categoryService;

    @PostMapping("admin/items/{itemId}")
    public String deleteItem(@PathVariable int itemId, Model model) {
        Item item = itemService.findItemById(itemId);
        if(item == null) {
            throw new RuntimeException("Item id not found - " + itemId);
        }
        itemService.deleteItem(itemId);
        return fetchAllItemsAndCategories(model);
    }

    @PostMapping("/admin/{categoryId}/submitItem")
    public String submitItem(@ModelAttribute("newItem") Item item, @PathVariable int categoryId, Model model) {
        item.setId(0);
        item.setCategory(categoryService.findCategoryById(categoryId));
        itemService.save(item);
        return fetchAllItemsAndCategories(model);
    }

    @PostMapping("/admin/{categoryId}/addItem")
    public String addItem(@PathVariable int categoryId, Model model) {
        Item item = new Item();
        model.addAttribute("newItem", item);
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("categoryId", categoryId);
        return "item-form";
    }

    @GetMapping("/item/{itemId}")
    public String getItemDescription(@PathVariable int itemId, Model model) {
        Item item = itemService.findItemById(itemId);
        List<Category> categories = categoryService.findAll();
        model.addAttribute("item", item);
        Category category = item.getCategory();
        model.addAttribute("categories", categories);
        model.addAttribute("categoryId", category.getId());
        return "item-page";
    }

    @PostMapping("/item/{itemId}")
    public String itemDescription(@PathVariable int itemId, Model model) {;
        return getItemDescription(itemId, model);
    }

    public String fetchAllItemsAndCategories(Model model) {
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

}