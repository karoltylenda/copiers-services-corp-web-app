package com.tytanisukcesu.copiers.repository;

import com.tytanisukcesu.copiers.entity.ServiceOrder;
import com.tytanisukcesu.copiers.types.ServiceOrderStatus;
import com.tytanisukcesu.copiers.types.ServiceOrderType;
import org.apache.tomcat.jni.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface ServiceOrderRepository extends JpaRepository<ServiceOrder,Long> {

    Optional<ServiceOrder> getServiceOrderByServiceOrderNumber(String serviceOrderNumber);

    Optional<ServiceOrder> getFirstByDevice_SerialNumberAndOrderStatusNotContaining(String serialNumber, ServiceOrderStatus serviceOrderStatus);

    Optional<ServiceOrder> getTopByOrderByOrderCreationDateDesc();

    List<ServiceOrder> findByOrderCreationDateBetweenOrderByOrderCreationDateDesc(LocalDateTime firstDay, LocalDateTime lastDay);


}
