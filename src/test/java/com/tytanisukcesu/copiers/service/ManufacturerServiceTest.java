package com.tytanisukcesu.copiers.service;

import com.tytanisukcesu.copiers.controller.ManufacturerController;
import com.tytanisukcesu.copiers.entity.Manufacturer;
import com.tytanisukcesu.copiers.service.exception.ModelNotFoundException;
import javassist.NotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@SpringBootTest
class ManufacturerServiceTest {

    @Autowired
    private ManufacturerService manufacturerService;

    @Autowired
    private ManufacturerController manufacturerController;

    @Test
    void shouldFindById() throws NotFoundException {
        //given
        Manufacturer manufacturer = manufacturerService.findById(1L);

        //then
        assertEquals(manufacturer.getId(), 1L);
        assertNotEquals(manufacturer.getId(), 2L);
        assertThat(manufacturer.getName()).isEqualTo("Xerox");
        assertThrows(ModelNotFoundException.class, () -> {
            manufacturerService.findById(5L);
        });
    }

    @Test
    void shouldAdd() {
        //given
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setName("Konica");

        //then
        assertEquals(manufacturer, manufacturerService.save(manufacturer));

    }

    @Test
    void shouldGetManufacturers() {
        //given
        ManufacturerService manufacturerService = Mockito.mock(ManufacturerService.class);

        //when
        when(manufacturerService.findAll()).thenReturn(prepareMockData());

        //then
        assertEquals(manufacturerService.findAll().size(),2);
    }

    private List<Manufacturer> prepareMockData() {
        List<Manufacturer> manufacturers = new ArrayList<>();
        Manufacturer manufacturerOne = new Manufacturer();
        manufacturerOne.setName("Xerox");
        Manufacturer manufacturerTwo = new Manufacturer();
        manufacturerTwo.setName("Konica");
        manufacturers.add(manufacturerOne);
        manufacturers.add(manufacturerTwo);
        return manufacturers;
    }

    //BDD
    @Test
    void shouldAddManufacturer(){
        //given
        ManufacturerService manufacturerService = Mockito.mock(ManufacturerService.class);
        Manufacturer givenManufacturer = new Manufacturer();
        givenManufacturer.setName("Utax");
        given(manufacturerService.save(Mockito.any(Manufacturer.class))).willReturn(givenManufacturer);

        //when
        Manufacturer manufacturer = manufacturerService.save(new Manufacturer());

        //then
        assertEquals(manufacturer.getName(),"Utax");
    }

}