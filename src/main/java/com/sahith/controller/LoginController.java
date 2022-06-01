package com.sahith.controller;


import com.sahith.utilities.CustomerSignIn;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping
    public String login(Model model) {
        CustomerSignIn customerSignIn = new CustomerSignIn();
        String str = "https://st.depositphotos.com/1031343/2194/v/600/depositphotos_21949883-stock-illustration-pet-shop-stamp.jpg";
        model.addAttribute("str" , str);
        model.addAttribute("customerSignIn", customerSignIn);
        model.addAttribute("signedUp", false);
        return "customer-signin";
    }
}