package com.tytanisukcesu.copiers.service;

import com.tytanisukcesu.copiers.entity.Counter;
import com.tytanisukcesu.copiers.entity.Device;
import com.tytanisukcesu.copiers.repository.CounterRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class CopierSettlementServiceTest {

    @Autowired
    private CounterRepository counterRepository;

    @Autowired
    private DeviceService deviceService;


    @Test
    void lastColourCounterTest2() {

//        Device device = deviceService.findById(1L);
//
//        LocalDate localDate = LocalDate.of(2021, 1, 31);
//
//        Optional<Counter> counterOptional = counterRepository.getTopByCounterDateBeforeAndDevice_SerialNumberOrderByCounterDateDesc(localDate, device.getSerialNumber());
//
//        assertThat(counterOptional).isPresent();
//
//        assertThat(counterOptional.get().getMonoCounter()).isEqualTo(7);
//        assertThat(counterOptional.get().getColourCounter()).isEqualTo(7);


    }

}