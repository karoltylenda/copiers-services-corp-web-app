package com.tytanisukcesu.copiers.servlet;


import com.tytanisukcesu.copiers.service.ContractService;
import com.tytanisukcesu.copiers.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/contracts")
public class ContractServlet {

    private final ContractService contractService;
    private final CustomerService customerService;

    @GetMapping
    public String findAll(Model model){
        model.addAttribute("contracts", contractService.findAll());
        model.addAttribute("customers", customerService.findAll());
        return ("pages/contracts");
    }
}
