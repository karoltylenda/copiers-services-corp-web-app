package com.tytanisukcesu.demo.controller;

import com.tytanisukcesu.demo.dto.CustomerDto;
import com.tytanisukcesu.demo.service.AddressService;
import com.tytanisukcesu.demo.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final AddressService addressService;

    @GetMapping(value = "/search")
    public List<CustomerDto> getAllByParameters(@RequestParam(required = false,defaultValue = "") String nip,
                                                @RequestParam(required = false,defaultValue = "") String companyName){
        return customerService.getAllByParameters(nip,companyName);
    }

    @GetMapping
    public List<CustomerDto> getAll(){
        return customerService.findAll();
    }

    @PostMapping
    public CustomerDto save(@RequestBody CustomerDto customerDto){
        return customerService.save(customerDto);
    }


    @GetMapping(value = "/{id}")
    public CustomerDto getById(@PathVariable("id") Long id){
        return customerService.getById(id);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id){
        if(customerService.delete(id)){
            return ResponseEntity.accepted().build();
        }else{
            return ResponseEntity.status(404).build();
        }
    }

    @PutMapping(value = "/{id}")
    public CustomerDto update(@PathVariable("id") Long id, @RequestBody CustomerDto customerDto){
        return customerService.update(id,customerDto);
    }


}
