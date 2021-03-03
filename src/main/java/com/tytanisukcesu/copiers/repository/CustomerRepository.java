package com.tytanisukcesu.copiers.repository;

import com.tytanisukcesu.copiers.entity.Customer;
import com.tytanisukcesu.copiers.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> getCustomerByTaxId(String taxId);

}
