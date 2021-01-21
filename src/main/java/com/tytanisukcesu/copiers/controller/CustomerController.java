package com.tytanisukcesu.copiers.controller;

import com.tytanisukcesu.copiers.dto.CustomerDto;
import com.tytanisukcesu.copiers.entity.Customer;
import com.tytanisukcesu.copiers.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final ModelMapper modelMapper;
//
//    @GetMapping(value = "/search")
//    public List<CustomerDto> getAllByParameters(@RequestParam(required = false,defaultValue = "") String nip,
//                                                @RequestParam(required = false,defaultValue = "") String companyName){
//        return customerService.getAllByParameters(nip,companyName);
//    }
//
    @GetMapping
    public List<CustomerDto> getAll(){
        List<Customer> customers = customerService.findAll();
        return customers.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public CustomerDto save(@RequestBody CustomerDto customerDto){
        Customer customer = convertToEntity(customerDto);
        Customer customerSaved = customerService.save(customer);
        return convertToDto(customerSaved);
    }

    @GetMapping(value = "/{id}")
    public CustomerDto findById(@PathVariable("id") Long id){
        Customer customer = customerService.findById(id);
        return convertToDto(customer);
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
        Customer customer = convertToEntity(customerDto);
        Customer customerUpdated = customerService.update(id,customer);
        return convertToDto(customerUpdated);
    }

    private CustomerDto convertToDto(Customer customer){
        CustomerDto customerDto = modelMapper.map(customer, CustomerDto.class);
        return customerDto;
    }

    private Customer convertToEntity(CustomerDto customerDto){
        Customer customer = modelMapper.map(customerDto, Customer.class);
        return customer;
    }
}
