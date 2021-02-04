package com.tytanisukcesu.copiers.service;

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

    @Test
    void mati() {
        LocalDate localDate = LocalDate.of(2021,01,30);
        LocalDate mati = copierSettlementService.mati();

        assertThat(localDate).isEqualTo(mati);


    }

    @Test
    void Karol(){
        LocalDate localDate = LocalDate.of(2021,01,31);
        LocalDate karol = copierSettlementService.getLastDay();

        assertThat(localDate).isEqualTo(karol);

    }
}