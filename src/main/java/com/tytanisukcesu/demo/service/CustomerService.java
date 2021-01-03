package com.tytanisukcesu.demo.service;

import com.tytanisukcesu.demo.dto.CustomerDto;
import com.tytanisukcesu.demo.dto.ManufacturerDto;
import com.tytanisukcesu.demo.entity.Customer;
import com.tytanisukcesu.demo.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    private CustomerDto provideDto(Customer customer){
        return CustomerDto.builder()
                .id(customer.getId())
                .address(customer.getAddress())
                .companyName(customer.getCompanyName())
                .companySiteUrl(customer.getCompanySiteUrl())
                .email(customer.getEmail())
                .nip(customer.getNip())
                .regon(customer.getRegon())
                .telephoneNumber(customer.getTelephoneNumber())
                .build();
    }

    private Customer provideDto(CustomerDto customerDto){
        return Customer.builder()
                .id(customerDto.getId())
                .address(customerDto.getAddress())
                .companyName(customerDto.getCompanyName())
                .companySiteUrl(customerDto.getCompanySiteUrl())
                .email(customerDto.getEmail())
                .nip(customerDto.getNip())
                .regon(customerDto.getRegon())
                .telephoneNumber(customerDto.getTelephoneNumber())
                .build();
    }

}
