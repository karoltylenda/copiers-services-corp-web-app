package com.tytanisukcesu.copiers.service;

import com.tytanisukcesu.copiers.entity.Device;
import org.apache.tomcat.jni.Local;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CopierSettlementServiceTest {

    @Autowired
    private CopierSettlementService copierSettlementService;

    @Autowired
    private DeviceService deviceService;

    @Test
    void check() {

        Integer intCheck = 5;

        Device device = deviceService.findById(1L);

//        Integer asd = copierSettlementService.getLastCounterMonoCounter(device);

//        assertThat(intCheck).isEqualTo(asd);



    }


}