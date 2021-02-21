package com.tytanisukcesu.copiers.repository;

import com.tytanisukcesu.copiers.entity.ServiceOrder;
import com.tytanisukcesu.copiers.types.ServiceOrderStatus;
import com.tytanisukcesu.copiers.types.ServiceOrderType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ServiceOrderRepository extends JpaRepository<ServiceOrder,Long> {


    Optional<ServiceOrder> getTopByOrderByOrderCreationDateDesc();

    List<ServiceOrder> findByOrderCreationDateBetweenOrderByOrderCreationDateDesc(LocalDateTime firstDay, LocalDateTime lastDay);

//    Optional<ServiceOrder> getFirstByDeviceSerialNumberAndOrderStatus(String serial,ServiceOrderStatus serviceOrderStatus);

    Optional<ServiceOrder> getFirstByDeviceSerialNumberAndOrderStatusAndOrderTypeNot(String serial, ServiceOrderStatus serviceOrderStatus, ServiceOrderType serviceOrderType);




}
