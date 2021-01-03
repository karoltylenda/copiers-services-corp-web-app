package com.tytanisukcesu.demo.servlet;

import com.tytanisukcesu.demo.dto.AddressDto;
import com.tytanisukcesu.demo.dto.ArticleDto;
import com.tytanisukcesu.demo.dto.CustomerDto;
import com.tytanisukcesu.demo.dto.ManufacturerDto;
import com.tytanisukcesu.demo.entity.Address;
import com.tytanisukcesu.demo.entity.Customer;
import com.tytanisukcesu.demo.service.AddressService;
import com.tytanisukcesu.demo.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequestMapping("/main/customers")
@RequiredArgsConstructor
public class CustomerServlet {

    private final CustomerService customerService;
    private final AddressService addressService;

    @GetMapping
    public String findAll(Model springModel){
        springModel.addAttribute("customers", customerService.findAll());
        return "customers";
    }

    @GetMapping(value = "/search")
    public String getAllByParameters(@RequestParam(required = false, defaultValue = "") String nip,
                                     @RequestParam(required = false, defaultValue = "") String companyName,
                                     Model springModel){
        springModel.addAttribute("customers", customerService.getAllByParameters(nip, companyName));
        return "customers";
    }

    @GetMapping(value = "/{id}")
    public String getById(@PathVariable("id") Long id, Model springModel){
        springModel.addAttribute("customer",customerService.getById(id));
        return "customer";
    }

    @PostMapping(value = "/save")
    public RedirectView save(@RequestParam AddressDto addressDto, @ModelAttribute CustomerDto customerDto){
        List<AddressDto> addressList = addressService.findAll();
        if(addressList.contains(addressDto)){
            //updare
        }else{

        }



//        if (manufacturerService.getByName(manufacturerName).isEmpty()) {
//            manufacturerDto.setName(manufacturerName);
//            manufacturerService.save(manufacturerDto);
//        } else {
//            manufacturerDto = manufacturerService.getByName(manufacturerName).get(0);
//            manufacturerService.update(manufacturerDto.getId(), manufacturerDto);
//        }

        if(

        articleDto.setManufacturer(manufacturerService.provideEntity(manufacturerDto));
        articleService.save(articleDto);
        return new RedirectView("/main/addArticleForm");
    }


}
