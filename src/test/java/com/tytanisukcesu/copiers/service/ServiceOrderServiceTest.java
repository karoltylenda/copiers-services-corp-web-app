package com.tytanisukcesu.copiers.service;

import com.tytanisukcesu.copiers.entity.Device;
import com.tytanisukcesu.copiers.entity.ServiceOrder;
import com.tytanisukcesu.copiers.repository.ServiceOrderRepository;
import com.tytanisukcesu.copiers.types.ServiceOrderStatus;
import com.tytanisukcesu.copiers.types.ServiceOrderType;
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

    @Autowired
    private DeviceService deviceService;

    @Test
    void getLastServiceOrder() {

        Optional<ServiceOrder> serviceOrderOptional = serviceOrderRepository.getTopByOrderByOrderCreationDateDesc();

        assertThat(serviceOrderOptional.get().getOrderCreationDate()).isEqualTo(LocalDateTime.now());

    }

    @Test
    void getServiceOrdersForMonth() {

        LocalDateTime one = LocalDateTime.now().with(TemporalAdjusters.firstDayOfMonth());
        LocalDateTime last = LocalDateTime.now().with(TemporalAdjusters.lastDayOfMonth());


        List<ServiceOrder> serviceOrderList = serviceOrderRepository.findByOrderCreationDateBetweenOrderByOrderCreationDateDesc(one, last);

        assertThat(serviceOrderList).isNotNull();
        assertThat(serviceOrderList.size()).isEqualTo(1);

    }

    @Test
    void getBySerialNumberAndStatusTest() {

        String serial = deviceService.findById(1L).getSerialNumber();

        Optional<ServiceOrder> serviceOrderOptional = serviceOrderRepository.getFirstByDeviceSerialNumberAndOrderStatusAndOrderTypeNot(serial, ServiceOrderStatus.NEW,ServiceOrderType.CONSUMABLE_DELIVERY);

        assertThat(serviceOrderOptional).isPresent();
        assertThat(serviceOrderOptional.get().getServiceOrderNumber()).isEqualTo("123123");
        assertThat(serviceOrderOptional.get().getOrderStatus()).isEqualTo(ServiceOrderStatus.COMPLETED);

    }

    @Test
    void getServiceOrderBySerialNumberAndStatusType() {

//        Device device = deviceService.findById(1L);
//
//        boolean check = serviceOrderService.checkIfServiceOrderExists(device.getSerialNumber());
//
//        assertThat(check).isTrue();

    }

    @Test
    void getBySerialNumberAndStatusAndType(){

        String serial = deviceService.findById(1L).getSerialNumber();

        Optional<ServiceOrder> serviceOrderOptional = serviceOrderRepository.getFirstByDeviceSerialNumberAndOrderStatusAndOrderTypeNot(serial, ServiceOrderStatus.NEW, ServiceOrderType.REPAIR);

        assertThat(serviceOrderOptional.get().getServiceOrderNumber()).isEqualTo("123123");

    }


}