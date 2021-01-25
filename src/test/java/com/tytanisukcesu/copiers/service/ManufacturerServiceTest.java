package com.tytanisukcesu.copiers.service;

import com.tytanisukcesu.copiers.entity.Manufacturer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ManufacturerServiceTest {

    @Autowired
    private ManufacturerService manufacturerService;

    @Test
    void shouldGetSingleManufacturer(){

        // given

        // when
        Manufacturer manufacturer = manufacturerService.findById(1L);

        // then
        assertThat(manufacturer).isNotNull();
        assertThat(manufacturer.getId()).isEqualTo(1L);

    }

}