package com.tytanisukcesu.copiers.repository;

import com.tytanisukcesu.copiers.entity.ServiceOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ServiceOrderRepository extends JpaRepository<ServiceOrder,Long> {

    Optional<ServiceOrder> getServiceOrderByServiceOrderNumber(String serviceOrderNumber);

}
