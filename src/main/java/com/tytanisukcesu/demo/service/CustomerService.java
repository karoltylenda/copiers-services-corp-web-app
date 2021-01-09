package com.tytanisukcesu.demo.service;

import com.tytanisukcesu.demo.dto.AddressDto;
import com.tytanisukcesu.demo.dto.CustomerDto;
import com.tytanisukcesu.demo.dto.ManufacturerDto;
import com.tytanisukcesu.demo.entity.Customer;
import com.tytanisukcesu.demo.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerDto provideDto(Customer customer){
        return CustomerDto.builder()
                .id(customer.getId())
                .addresses(customer.getAddresses())
                .companyName(customer.getCompanyName())
                .companySiteUrl(customer.getCompanySiteUrl())
                .email(customer.getEmail())
                .nip(customer.getNip())
                .regon(customer.getRegon())
                .telephoneNumber(customer.getTelephoneNumber())
                .build();
    }

    public Customer provideEntity(CustomerDto customerDto){
        return Customer.builder()
                .id(customerDto.getId())
                .addresses(customerDto.getAddresses())
                .companyName(customerDto.getCompanyName())
                .companySiteUrl(customerDto.getCompanySiteUrl())
                .email(customerDto.getEmail())
                .nip(customerDto.getNip())
                .regon(customerDto.getRegon())
                .telephoneNumber(customerDto.getTelephoneNumber())
                .build();
    }

    public CustomerDto save(CustomerDto customerDto){
        Customer customer = provideEntity(customerDto);
        customerRepository.save(customer);
        return provideDto(customer);
    }

    public boolean delete(Long id){
        Optional<Customer> customer = customerRepository.findById(id);
        if(customer.isPresent()){
            customerRepository.delete(customer.get());
            return true;
        }else{
            return false;
        }
    }

    //FIXME - return do zmiany?
    public CustomerDto getById(Long id){
        Optional<Customer> customer = customerRepository.findById(id);
        return provideDto(customer.orElse(new Customer()));
    }

    public List<CustomerDto> findAll(){
        List<Customer> customers = customerRepository.findAll();
        return customers.stream()
                .map(this::provideDto)
                .collect(Collectors.toList());
    }

    public CustomerDto update(Long id, CustomerDto customerDto){
        Customer customer = customerRepository.findById(id).orElseThrow();
        Customer customerUpdated = provideEntity(customerDto);
        customer.setAddresses(customerUpdated.getAddresses());
        customer.setCompanyName(customerUpdated.getCompanyName());
        customer.setCompanySiteUrl(customerUpdated.getCompanySiteUrl());
        customer.setEmail(customerUpdated.getEmail());
        customer.setId(customerUpdated.getId());
        customer.setNip(customerUpdated.getNip());
        customer.setRegon(customerUpdated.getRegon());
        customer.setTelephoneNumber(customerUpdated.getTelephoneNumber());
        return provideDto(customer);
    }

    public List<CustomerDto> getAllByParameters(String nip,String companyName){
        List<Customer> customers = customerRepository.getCustomerByNipContainsAndCompanyNameContains(nip,companyName);
        return customers.stream()
                .map(this::provideDto)
                .collect(Collectors.toList());
    }

//    private CustomerDto checkIfAddressExists(CustomerDto customerDto) {
//
//        Optional<AddressDto> addressIfExists = addressService.findAddressIfExists(addressService.provideDto(customerDto.getAddresses()));
//        if (addressIfExists.get().getId() != null) {
//            customerDto.setAddresses(addressService.provideEntity(addressIfExists.get()));
//        } else {
//            customerDto.setAddresses(addressService.provideEntity(addressService.save(addressIfExists.get())));
//        }
//        return customerDto;
//    }

}
