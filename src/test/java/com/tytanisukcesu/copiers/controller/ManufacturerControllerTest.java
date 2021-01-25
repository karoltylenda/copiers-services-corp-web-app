package com.tytanisukcesu.copiers.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tytanisukcesu.copiers.entity.Device;
import com.tytanisukcesu.copiers.entity.Manufacturer;
import com.tytanisukcesu.copiers.entity.Model;
import com.tytanisukcesu.copiers.repository.DeviceRepository;
import com.tytanisukcesu.copiers.repository.ManufacturerRepository;
import com.tytanisukcesu.copiers.service.ManufacturerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
class ManufacturerControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ManufacturerService manufacturerService;

    @Test
    @Transactional
    void shouldGetSingleManufacturer() throws Exception {

        // given
        Manufacturer newManufacturer = new Manufacturer();
        newManufacturer.setName("Test123");
        manufacturerService.save(newManufacturer);


        // when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/manufacturers/" + newManufacturer.getId()))
                .andDo(print())
                .andExpect(status().is(200))
                .andReturn();

        //then
        Manufacturer manufacturer = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Manufacturer.class);
        assertThat(manufacturer).isNotNull();
        assertThat(manufacturer.getId()).isEqualTo(newManufacturer.getId());
        assertThat(manufacturer.getName()).isEqualTo(newManufacturer.getName());
        assertThat(manufacturer.getName()).isEqualTo("Test123");




    }

}