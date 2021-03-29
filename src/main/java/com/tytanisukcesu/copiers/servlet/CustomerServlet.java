package com.tytanisukcesu.copiers.servlet;

import com.tytanisukcesu.copiers.dto.CustomerDto;
import com.tytanisukcesu.copiers.entity.Customer;
import com.tytanisukcesu.copiers.service.AddressService;
import com.tytanisukcesu.copiers.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerServlet {

    private final CustomerService customerService;
    private final AddressService addressService;
    private final ModelMapper modelMapper;

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("customers", customerService.findAll());
        return "pages/customers";
    }

    @PostMapping
    public RedirectView addCustomer(CustomerDto customerDto){
        Customer customer = customerService.save(convertToEntity(customerDto));
        return new RedirectView("/customers");
    }

    @PostMapping(value = "/delete")
    public RedirectView deleteCustomer(CustomerDto customerDto){
        customerService.delete(customerDto.getId());
        return new RedirectView("/customers");
    }

    @PostMapping(value = "/update")
    public RedirectView updateCustomer(CustomerDto customerDto){
        System.out.println(customerDto.toString());
        customerService.updateFromServlet(convertToEntity(customerDto));
        return new RedirectView("/customers");
    }


    private Customer convertToEntity(CustomerDto customerDto){
        return modelMapper.map(customerDto, Customer.class);
    }

}
