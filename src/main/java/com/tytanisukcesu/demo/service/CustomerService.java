package com.tytanisukcesu.demo.service;

import com.tytanisukcesu.demo.entity.Customer;
import com.tytanisukcesu.demo.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public Customer save(Customer customer) {
        Optional<Customer> customerOptional = customerRepository.getByNip(customer.getNip());
        if (customerOptional.isPresent()) {
            return customerOptional.get();
        } else {
            return customerRepository.save(customer);
        }
    }

    public Customer findById(Long id) {
        return customerRepository.findById(id).orElse(new Customer());
    }

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public boolean deleteById(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            customerRepository.delete(customer.get());
            return true;
        } else {
            return false;
        }
    }

    @Transactional
    public Customer update(Long id, Customer customer) {
        Customer customerToFind = customerRepository.findById(id).orElse(new Customer());
        customerToFind.setAddresses(customer.getAddresses());
        customerToFind.setCompanyName(customer.getCompanyName());
        customerToFind.setModels(customer.getModels());
        customerToFind.setEmail(customer.getEmail());
        customerToFind.setTelephoneNumber(customer.getTelephoneNumber());
        customerToFind.setNip(customer.getNip());
        customerToFind.setCompanySiteUrl(customer.getCompanySiteUrl());
        customerToFind.setRegon(customer.getRegon());
        return customerToFind;
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
