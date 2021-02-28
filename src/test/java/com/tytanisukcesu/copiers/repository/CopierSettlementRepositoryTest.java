package com.tytanisukcesu.copiers.repository;

import com.tytanisukcesu.copiers.entity.CopierSettlement;
import com.tytanisukcesu.copiers.entity.Device;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDate;
import java.util.Optional;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class CopierSettlementRepositoryTest {

    @Autowired
    private CopierSettlementRepository copierSettlementRepository;

    @Autowired
    private DeviceRepository deviceRepository;

    @Test
    void getTopByDateOfSettlementAndContractDeviceSerialNumberOrderByDateOfSettlementDesc(){

        Device device = deviceRepository.findById(1L).get();

        LocalDate localDate = LocalDate.of(2021, 3, 28);

        Optional<CopierSettlement> optionalCopierSettlement = copierSettlementRepository.getTopByDateOfSettlementBeforeAndContract_Device_SerialNumberOrderByDateOfSettlementDesc(localDate, device.getSerialNumber());

        assertThat(optionalCopierSettlement.isPresent());

        assertThat(optionalCopierSettlement.get().getDateOfSettlement().getMonth().getValue()).isEqualTo(2);
    }
}