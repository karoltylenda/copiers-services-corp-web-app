package com.tytanisukcesu.copiers.servlet;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainServlet {

    @GetMapping("/main/addManufacturerForm")
    private String addManufacturerForm() {
        return "addManufacturerForm";
    }

    @GetMapping("/main/addModelForm")
    private String addModelForm() {
        return "addModelForm";
    }

    @GetMapping("/main/addArticleForm")
    private String addArticleForm() {
        return "addArticleForm";
    }


    @GetMapping("/main/addCustomerForm")
    private String addCustomerForm() {
        return "addCustomerForm";
    }


}
