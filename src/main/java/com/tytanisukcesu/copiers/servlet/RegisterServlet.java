package com.tytanisukcesu.copiers.servlet;

import com.tytanisukcesu.copiers.entity.User;
import com.tytanisukcesu.copiers.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/register")
public class RegisterServlet {

    private final UserService userService;

    @GetMapping
    public String signUp(Model model){
        model.addAttribute("user",new User());
        return "register-form";
    }

    @PostMapping
    public String register(User user){
        userService.save(user);
        return "register-form";
    }

    @GetMapping("/test123")
    public String test123(Model model, Principal principal){
        model.addAttribute("name",principal.getName());
        return "test123";
    }



}
