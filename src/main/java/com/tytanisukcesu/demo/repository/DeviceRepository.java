package com.tytanisukcesu.demo.repository;

import com.tytanisukcesu.demo.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {


    Optional<Device> getDeviceBySerialNumber(String serialNumber);
}
