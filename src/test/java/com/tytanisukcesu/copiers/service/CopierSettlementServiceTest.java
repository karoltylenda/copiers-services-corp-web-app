package com.tytanisukcesu.copiers.service;

import com.tytanisukcesu.copiers.entity.Device;
import org.apache.tomcat.jni.Local;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
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
    void getMonoAmountTest() {

        BigDecimal colourAmount = copierSettlementService.getMonoAmount(BigDecimal.valueOf(2.0), 10, 20);
        BigDecimal check = new BigDecimal("20.0");
        boolean bool = colourAmount.equals(check);

        assertThat(bool).isTrue();
        assertThat(colourAmount).isGreaterThanOrEqualTo(new BigDecimal("20.0"));

    }

    @Test
    void getColourAmountTest(){
        BigDecimal colourAmount = copierSettlementService.getColourAmount(new BigDecimal("2.0"), 10, 20);
        BigDecimal check = new BigDecimal("19.0");
        boolean bool = colourAmount.equals(check);

        assertThat(bool).isTrue();
        assertThat(colourAmount).isGreaterThanOrEqualTo(new BigDecimal("20.0"));

    }


}