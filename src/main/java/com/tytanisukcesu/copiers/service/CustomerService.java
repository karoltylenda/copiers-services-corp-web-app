package com.tytanisukcesu.copiers.service;

import com.tytanisukcesu.copiers.entity.Customer;
import com.tytanisukcesu.copiers.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public Customer findById(Long id) {
        return customerRepository.findById(id).orElse(new Customer());
    }

    public Customer save(Customer customer) {
        Optional<Customer> customerOptional = customerRepository.getCustomerByTaxId(customer.getTaxId());
        if (customerOptional.isPresent()) {
            return customerOptional.get();
        } else {
            Customer customerSaved = customerRepository.save(customer);
            return customerSaved;
        }
    }

    public boolean delete(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            customerRepository.delete(customer.get());
            return true;
        } else {
            return false;
        }
    }

    public Customer update(Long id, Customer customer) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if (customerOptional.isPresent()) {
            Customer customerUpdated = customerOptional.get();
            customerUpdated.setCompanyName(customer.getCompanyName());
            customerUpdated.setEmail(customer.getEmail());
            customerUpdated.setCompanySiteUrl(customer.getCompanySiteUrl());
            customerUpdated.setRegon(customer.getRegon());
            customerUpdated.setTelephoneNumber(customer.getTelephoneNumber());
            customerUpdated.setAddress(customer.getAddress());
            customerUpdated.setContract(customer.getContract());
            customerUpdated.setDevices(customer.getDevices());
            customerUpdated.setEmail(customer.getEmail());
            customerUpdated.setTaxId(customer.getTaxId());
            return customerUpdated;
        } else {
            return new Customer();
        }
    }

}
