package com.tytanisukcesu.copiers.servlet;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/")
public class HomeServlet {

    @GetMapping
    public RedirectView main() {
        return new RedirectView("/home");
    }

    @GetMapping(value = "/home")
    public String home(){
        return ("pages/dashboard");
    }
}
