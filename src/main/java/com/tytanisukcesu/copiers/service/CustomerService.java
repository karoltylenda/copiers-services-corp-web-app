package com.tytanisukcesu.copiers.service;

import com.tytanisukcesu.copiers.entity.Customer;
import com.tytanisukcesu.copiers.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private static final Logger LOGGER = Logger.getLogger(CustomerService.class.getName());

    public List<Customer> findAll() {
        List<Customer> customers = customerRepository.findAll();
        return customers;
    }

    public Customer findById(Long id) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        return customerOptional.orElse(new Customer());
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

    @Transactional
    public Customer update(Long id, Customer customer) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if (customerOptional.isPresent()) {
            Customer customerToUpdate = customerOptional.get();
            customerToUpdate.setCompanyName(customer.getCompanyName());
            customerToUpdate.setEmail(customer.getEmail());
            customerToUpdate.setCompanySiteUrl(customer.getCompanySiteUrl());
            customerToUpdate.setRegon(customer.getRegon());
            customerToUpdate.setTelephoneNumber(customer.getTelephoneNumber());
            customerToUpdate.setAddress(customer.getAddress());
//            customerToUpdate.setContract(customer.getContract());
            customerToUpdate.setDevices(customer.getDevices());
            customerToUpdate.setEmail(customer.getEmail());
            customerToUpdate.setTaxId(customer.getTaxId());
            return customerToUpdate;
        } else {
            return new Customer();
        }
    }

}
