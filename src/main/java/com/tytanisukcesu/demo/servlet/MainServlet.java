package com.tytanisukcesu.demo.servlet;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainServlet {

    @GetMapping("/main/addManufacturerForm")
    private String addManufacturerForm(){
        return "addManufacturerForm";
    }
}
