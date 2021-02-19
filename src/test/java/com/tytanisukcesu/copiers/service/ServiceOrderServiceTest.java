package com.tytanisukcesu.copiers.service;

import com.tytanisukcesu.copiers.entity.ServiceOrder;
import com.tytanisukcesu.copiers.repository.ServiceOrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ServiceOrderServiceTest {

    @Autowired
    private ServiceOrderRepository serviceOrderRepository;

    @Test
    void getLastServiceOrder(){

        Optional<ServiceOrder> serviceOrderOptional = serviceOrderRepository.getTopByOrderByOrderCreationDateDesc();

        assertThat(serviceOrderOptional.get().getOrderCreationDate()).isEqualTo(LocalDateTime.now());

    }

}