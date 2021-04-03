package com.tytanisukcesu.copiers.servlet;


import com.tytanisukcesu.copiers.service.ServiceOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/serviceOrders")
public class ServiceOrderServlet {

    private final ServiceOrderService serviceOrderService;

    @GetMapping
    public String findAll(final Model model){
        model.addAttribute("serviceOrders", serviceOrderService.findAll());
        return ("pages/serviceOrders");
    }
}
