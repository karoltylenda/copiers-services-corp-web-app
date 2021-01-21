package com.tytanisukcesu.copiers.servlet;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/main/customers")
@RequiredArgsConstructor
public class CustomerServlet {

//    private final CustomerService customerService;
//    private final AddressService addressService;
//
//    @GetMapping
//    public String findAll(Model springModel) {
//        springModel.addAttribute("customers", customerService.findAll());
//        return "customers";
//    }
//
//    @GetMapping(value = "/search")
//    public String getAllByParameters(@RequestParam(required = false, defaultValue = "") String nip,
//                                     @RequestParam(required = false, defaultValue = "") String companyName,
//                                     Model springModel) {
//        springModel.addAttribute("customers", customerService.getAllByParameters(nip, companyName));
//        return "customers";
//    }
//
//    @GetMapping(value = "/{id}")
//    public String getById(@PathVariable("id") Long id, Model springModel) {
//        springModel.addAttribute("customer", customerService.getById(id));
//        return "customer";
//    }
//
//    @PostMapping(value = "/save")
//    public RedirectView save(@ModelAttribute CustomerDto customerDto) {
//        customerService.save(customerDto);
//        return new RedirectView("/main/addCustomerForm");
//    }
//
//

}
