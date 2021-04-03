package com.tytanisukcesu.copiers.servlet;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/settings")
public class SettingServlet {

    @GetMapping
    public String settings(){
        return ("pages/settings");
    }
}
