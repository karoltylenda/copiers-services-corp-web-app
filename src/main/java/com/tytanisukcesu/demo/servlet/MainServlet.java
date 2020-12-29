package com.tytanisukcesu.demo.servlet;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller

public class MainServlet {

    @GetMapping("/main/addManufacturerForm")
    private String addManufacturerForm(){
        return "addManufacturerForm";
    }
    @GetMapping("/main/addModelForm")
    private String addModelForm(){
        return "addModelForm";
    }
}
