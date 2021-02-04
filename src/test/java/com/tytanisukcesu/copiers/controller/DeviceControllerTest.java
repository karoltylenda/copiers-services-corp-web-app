package com.tytanisukcesu.copiers.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tytanisukcesu.copiers.entity.Device;
import com.tytanisukcesu.copiers.entity.Manufacturer;
import com.tytanisukcesu.copiers.entity.Model;
import com.tytanisukcesu.copiers.repository.DeviceRepository;
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
        //token
class DeviceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private DeviceRepository deviceRepository;

    @Test
    @Transactional
        //wszystko co mamy w tescie bedzie wywolane w 1 trabsakcji - test zostanie usuniety z bazy
    void shouldGetSingleDevice() throws Exception {

        // given
        Device newDevice = new Device();
        Model model = new Model();
        Manufacturer manufacturer = new Manufacturer();
        model.setName("C230");
        newDevice.setModel(model);
        newDevice.setSerialNumber("1");
//        newDevice.setMonoPagePrice(new BigDecimal("0.3"));
//        newDevice.setColorPagePrice(new BigDecimal("1.0"));
        deviceRepository.save(newDevice);

        // when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/devices/" + newDevice.getId()))
                .andDo(print())
                .andExpect(status().is(200))
                .andReturn();

        //then
        Device device = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Device.class);
        assertThat(device).isNotNull();
        assertThat(device.getId()).isEqualTo(newDevice.getId());
        assertThat(device.getModel().getName()).isEqualTo(newDevice.getModel().getName());
        assertThat(device.getSerialNumber()).isEqualTo(newDevice.getSerialNumber());


    }


}