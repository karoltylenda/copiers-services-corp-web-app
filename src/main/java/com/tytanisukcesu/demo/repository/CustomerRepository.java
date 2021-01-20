package com.tytanisukcesu.demo.repository;

import com.tytanisukcesu.demo.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

//    List<Customer> getCustomerByNipContainsAndCompanyNameContains(String nip,String companyName);

}
