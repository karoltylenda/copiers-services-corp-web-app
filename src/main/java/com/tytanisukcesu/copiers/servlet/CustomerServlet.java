package com.tytanisukcesu.copiers.servlet;

import com.tytanisukcesu.copiers.service.AddressService;
import com.tytanisukcesu.copiers.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerServlet {

    private final CustomerService customerService;
    private final AddressService addressService;

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("customers", customerService.findAll());
        return "pages/customers";
    }

}
