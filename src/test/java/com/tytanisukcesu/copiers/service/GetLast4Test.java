package com.tytanisukcesu.copiers.service;

import org.apache.tomcat.jni.Local;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class GetLast4Test {

    @Autowired
    private CopierSettlementService copierSettlementService;

//    @Test
//    void getLast() {
//        LocalDate localDateCheck = LocalDate.of(2021,01,20);
//        LocalDate localDate = copierSettlementService.getClosest();
//        assertThat(localDate).isEqualTo(localDateCheck);
//
//    }


}
