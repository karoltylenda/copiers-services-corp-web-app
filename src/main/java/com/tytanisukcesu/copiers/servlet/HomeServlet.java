package com.tytanisukcesu.copiers.servlet;

import com.tytanisukcesu.copiers.service.ContractService;
import com.tytanisukcesu.copiers.service.CustomerService;
import com.tytanisukcesu.copiers.service.ServiceOrderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class HomeServlet {

    private final CustomerService customerService;
    private final ContractService contractService;
    private final ServiceOrderService serviceOrderService;


    @GetMapping
    public RedirectView main() {
        return new RedirectView("/home");
    }

    @GetMapping(value = "/home")
    public String home(Model model){
        model.addAttribute("customers", customerService.findAll());
        model.addAttribute("contracts", contractService.findAll());
        model.addAttribute("serviceOrders", serviceOrderService.findAll());
        return ("pages/dashboard");
    }
}
