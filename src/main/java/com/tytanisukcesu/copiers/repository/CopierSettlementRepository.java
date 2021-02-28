package com.tytanisukcesu.copiers.repository;

import com.tytanisukcesu.copiers.entity.CopierSettlement;
import com.tytanisukcesu.copiers.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface CopierSettlementRepository extends JpaRepository<CopierSettlement, Long> {


    Optional<CopierSettlement> getTopByContract_DeviceOrderByDateOfSettlementDesc(Device device);

    Optional<CopierSettlement> getTopByDateOfSettlementBeforeAndContract_Device_SerialNumberOrderByDateOfSettlementDesc(LocalDate localDate, String serialNumber);
}
