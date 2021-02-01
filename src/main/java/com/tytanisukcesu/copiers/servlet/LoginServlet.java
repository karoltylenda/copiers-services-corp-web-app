package com.tytanisukcesu.copiers.servlet;

import com.tytanisukcesu.copiers.configuration.LoginCredentials;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

//@Controller
//public class LoginServlet {
//
//    @PostMapping("/login")
//    public String login(@RequestBody LoginCredentials loginCredentials,Model model){
//        model.addAttribute("login",loginCredentials);
//        return  "redirect:/secured";
//    }
//
//    @GetMapping("/secured")
//    public String secured(Model model) {
//        model.addAttribute("login", new LoginCredentials());
//        return "secured";
//    }
//
//}
