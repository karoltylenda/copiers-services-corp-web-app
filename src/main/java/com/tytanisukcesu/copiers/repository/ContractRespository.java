package com.tytanisukcesu.copiers.repository;

import com.tytanisukcesu.copiers.entity.Contract;
import com.tytanisukcesu.copiers.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContractRespository extends JpaRepository<Contract, Long> {

//    Optional<Contract> findContractByDevice_SerialNumber(String serialNumber);

    Optional<Contract> getContractByDevice_SerialNumber(String serialNumber);

}
