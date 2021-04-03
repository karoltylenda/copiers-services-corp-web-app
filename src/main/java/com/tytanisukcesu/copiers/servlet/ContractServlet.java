package com.tytanisukcesu.copiers.servlet;


import com.tytanisukcesu.copiers.service.ContractService;
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

    @GetMapping
    public String findAll(Model model){
        model.addAttribute("contracts", contractService.findAll());
        return ("pages/contracts");
    }
}
