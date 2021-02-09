package com.tytanisukcesu.copiers.repository;

import com.tytanisukcesu.copiers.entity.CopierSettlement;
import com.tytanisukcesu.copiers.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CopierSettlementRepository extends JpaRepository<CopierSettlement, Long> {

//    Optional<CopierSettlement> getTopByDeviceOrderByDateOfSettlementDesc(Device device);

    Optional<CopierSettlement> getTopByContract_DeviceOrderByDateOfSettlementDesc(Device device);
}
