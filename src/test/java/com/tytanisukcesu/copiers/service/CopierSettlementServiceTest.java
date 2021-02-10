package com.tytanisukcesu.copiers.service;

import com.tytanisukcesu.copiers.entity.Counter;
import com.tytanisukcesu.copiers.entity.Device;
import com.tytanisukcesu.copiers.repository.CounterRepository;
import org.apache.tomcat.jni.Local;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CopierSettlementServiceTest {

    @Autowired
    private CounterRepository counterRepository;

    @Autowired
    private DeviceService deviceService;

    @Test
    void lastColourCounterTest() {

        Device device = deviceService.findById(1L);

        LocalDate localDate = LocalDate.now();

        Optional<Counter> counterOptional = counterRepository.getTopByCounterDateAndDevice_SerialNumber(localDate, device.getSerialNumber());

        assertThat(counterOptional).isPresent();

        assertThat(counterOptional.get().getMonoCounter()).isEqualTo(10);


    }

    @Test
    void lastColourCounterTest2() {

        Device device = deviceService.findById(1L);

        LocalDate localDate = LocalDate.of(2021, 1, 31);

        Optional<Counter> counterOptional = counterRepository.getTopByCounterDateBeforeAndDevice_SerialNumber(localDate, device.getSerialNumber());

        assertThat(counterOptional).isPresent();

        assertThat(counterOptional.get().getMonoCounter()).isEqualTo(7);
        assertThat(counterOptional.get().getColourCounter()).isEqualTo(7);


    }

    @Test
    void lastColourCounterTest3() {
        //znajdowanie dla id

        Device device = deviceService.findById(1L);

        LocalDate localDate = LocalDate.of(2021, 1, 31);

        Optional<Counter> counterOptional = counterRepository.test(9L);

        assertThat(counterOptional).isPresent();

        assertThat(counterOptional.get().getMonoCounter()).isEqualTo(11);
    }

    @Test
    void lastColourCounterTest4() {
        //znajdowanie dla serialnumber i okreslonej daty

        Device device = deviceService.findById(1L);

        LocalDate localDate = LocalDate.of(2021, 1, 31);

        Optional<Counter> counterOptional = counterRepository.test2(device.getSerialNumber(),localDate);

        assertThat(counterOptional).isPresent();

        assertThat(counterOptional.get().getMonoCounter()).isEqualTo(7);
    }

    @Test
    void lastColourCounterTest5() {
        //znajdowanie dla serialnumber i okreslonej daty lub mniejszej

        Device device = deviceService.findById(1L);

        LocalDate localDate = LocalDate.of(2021, 1, 31);

        Optional<Counter> counterOptional = counterRepository.test3(device.getSerialNumber(),localDate);

        assertThat(counterOptional).isPresent();

        assertThat(counterOptional.get().getMonoCounter()).isEqualTo(10);
    }

    @Test
    void lastColourCounterTest6() {
        //znajdowanie max dla serialnumber i okreslonej daty lub mniejszej

        Device device = deviceService.findById(1L);

        LocalDate localDate = LocalDate.of(2021, 1, 31);

        Optional<Counter> counterOptional = counterRepository.findFirstByCounterDateBeforeAndDevice_SerialNumberOrderByTotalCounterDesc(localDate,device.getSerialNumber());

        assertThat(counterOptional).isPresent();

        assertThat(counterOptional.get().getTotalCounter()).isEqualTo(9);
    }


}