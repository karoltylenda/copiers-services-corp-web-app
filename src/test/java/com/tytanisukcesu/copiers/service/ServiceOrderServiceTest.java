package com.tytanisukcesu.copiers.service;

import com.tytanisukcesu.copiers.entity.ServiceOrder;
import com.tytanisukcesu.copiers.repository.ServiceOrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ServiceOrderServiceTest {

    @Autowired
    private ServiceOrderRepository serviceOrderRepository;

    @Autowired
    private ServiceOrderService serviceOrderService;

    @Test
    void getLastServiceOrder(){

        Optional<ServiceOrder> serviceOrderOptional = serviceOrderRepository.getTopByOrderByOrderCreationDateDesc();

        assertThat(serviceOrderOptional.get().getOrderCreationDate()).isEqualTo(LocalDateTime.now());

    }

    @Test
    void getServiceOrdersForMonth(){

        LocalDateTime one = LocalDateTime.now().with(TemporalAdjusters.firstDayOfMonth());
        LocalDateTime last = LocalDateTime.now().with(TemporalAdjusters.lastDayOfMonth());



        List<ServiceOrder> serviceOrderList = serviceOrderRepository.findByOrderCreationDateBetweenOrderByOrderCreationDateDesc(one,last);

        assertThat(serviceOrderList).isNotNull();
        assertThat(serviceOrderList.size()).isEqualTo(1);

    }


}