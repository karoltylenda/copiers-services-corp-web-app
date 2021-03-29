package com.tytanisukcesu.copiers.service;

import com.tytanisukcesu.copiers.entity.Customer;
import com.tytanisukcesu.copiers.entity.User;
import com.tytanisukcesu.copiers.repository.CustomerRepository;
import com.tytanisukcesu.copiers.service.exception.ModelNotFoundException;
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
    private final AddressService addressService;
    private static final Logger LOGGER = Logger.getLogger(CustomerService.class.getName());

    public List<Customer> findAll() {
        List<Customer> customers = customerRepository.findAll();
        return customers;
    }

    public Customer findById(Long id) {
        return customerRepository.findById(id).orElseThrow(()->new ModelNotFoundException(id,"customer"));
    }

    @Transactional
    public Customer save(Customer customer) {
        Optional<Customer> customerOptional = customerRepository.getCustomerByTaxId(customer.getTaxId());
        if (customerOptional.isPresent()) {
            return customerOptional.get();
        } else {
            Customer customerSaved = customerRepository.save(customer);
            LOGGER.info("A new row has been added.");
            return customerSaved;
        }
    }

    public boolean delete(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            customerRepository.delete(customer.get());
            LOGGER.info("Customer for id " + id + " has been deleted");
            return true;
        } else {
            LOGGER.warning("Customer for id " + id + " has not been deleted");
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
            customerToUpdate.setUsers(customer.getUsers());
            customerToUpdate.setDevices(customer.getDevices());
            customerToUpdate.setEmail(customer.getEmail());
            customerToUpdate.setTaxId(customer.getTaxId());
            LOGGER.info(customerToUpdate.getCompanyName() + " with TAX ID " +customerToUpdate.getTaxId() + " for id " + customerToUpdate.getId() + " has been updated.");
            return customerToUpdate;
        } else {
            LOGGER.warning("Customer for id " + id + " has not been found");
            throw new ModelNotFoundException(id,"customer");        }
    }

    @Transactional
    public Customer updateFromServlet(Customer customer) {
        Optional<Customer> customerOptional = customerRepository.findById(customer.getId());
        if (customerOptional.isPresent()) {
            Customer customerToUpdate = customerOptional.get();
            customerToUpdate.setCompanyName(customer.getCompanyName());
            customerToUpdate.setEmail(customer.getEmail());
            customerToUpdate.setCompanySiteUrl(customer.getCompanySiteUrl());
            customerToUpdate.setRegon(customer.getRegon());
            customerToUpdate.setTelephoneNumber(customer.getTelephoneNumber());
            customerToUpdate.setAddress(addressService.save(customer.getAddress()));
            customerToUpdate.setEmail(customer.getEmail());
            LOGGER.info(customerToUpdate.getCompanyName() + " with TAX ID " +customerToUpdate.getTaxId() + " for id " + customerToUpdate.getId() + " has been updated.");
            return customerToUpdate;
        } else {
            LOGGER.warning("Customer for id " + customer.getId() + " has not been found");
            throw new ModelNotFoundException(customer.getId(),"customer");        }
    }
}
