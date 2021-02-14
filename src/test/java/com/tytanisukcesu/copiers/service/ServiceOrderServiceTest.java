package com.tytanisukcesu.copiers.service;

import com.tytanisukcesu.copiers.entity.ServiceOrder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class ServiceOrderServiceTest {

    @Autowired
    private ServiceOrderService serviceOrderService;

    @Test
    void test(){

        ServiceOrder serviceOrder = serviceOrderService.findById(2L);

        ServiceOrder serviceOrderTest = serviceOrderService.getTop();

        assertThat(serviceOrderTest.getServiceOrderNumber()).isEqualTo(serviceOrder.getServiceOrderNumber());
    }
}