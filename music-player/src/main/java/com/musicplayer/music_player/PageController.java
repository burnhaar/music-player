package com.musicapp.musicapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/")
    public String redirectToLogin() {
        return "redirect:/index.html";
    }

    @GetMapping("/register")
    public String registerPage() {
        // Serves register.html
        return "register";
    }

    @GetMapping("/home")
    public String homePage() {
        // Serves home.html
        return "home";
    }
}