package com.tytanisukcesu.copiers.repository;

import com.tytanisukcesu.copiers.entity.ServiceOrder;
import org.apache.tomcat.jni.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface ServiceOrderRepository extends JpaRepository<ServiceOrder,Long> {

    Optional<ServiceOrder> getServiceOrderByServiceOrderNumber(String serviceOrderNumber);

    Optional<ServiceOrder> getTopByServiceOrderNumberOrderByServiceOrderNumberDesc();
}
