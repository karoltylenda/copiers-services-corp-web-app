package com.tytanisukcesu.copiers.repository;

import com.tytanisukcesu.copiers.dto.DeviceDto;
import com.tytanisukcesu.copiers.entity.CopierSettlement;
import com.tytanisukcesu.copiers.service.DeviceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class CopierSettlementRepositoryTest {

    @Autowired
    private CopierSettlementRepository copierSettlementRepository;

    @Autowired
    private DeviceService deviceService;

    @Test
    void findLatestSettlementForDeviceTest(){

//        DeviceDto deviceDto = deviceService.findById(1L);
//
//        CopierSettlement copierSettlement = copierSettlementRepository.getDistinctFirstByContract_Device(deviceDto).get();
//
//        assertThat(copierSettlement).isNotNull();
//
//        assertThat(copierSettlement.getContract().getDevice()).isEqualTo(deviceDto);
//
//        assertThat(copierSettlement.getDateOfSettlement()).isEqualTo("2021-02-01");

    }

}
