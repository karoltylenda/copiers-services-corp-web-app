package com.tytanisukcesu.demo.service;

import com.tytanisukcesu.demo.entity.Device;
import com.tytanisukcesu.demo.repository.DeviceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DeviceService {

    private final DeviceRepository deviceRepository;
    private final ModelService modelService;

    public List<Device> findAll() {
        return deviceRepository.findAll();
    }

    public Device findById(Long id) {
        Optional<Device> deviceOptional = deviceRepository.findById(id);
        return deviceOptional.orElse(new Device());
    }

    @Transactional
    public Device save(Device device) {
        Optional<Device> deviceOptional = deviceRepository.getDeviceBySerialNumber(device.getSerialNumber());
        if (deviceOptional.isPresent()){
            return deviceOptional.get();
        } else {
            Device deviceToSave = new Device();
            deviceToSave.setSerialNumber(device.getSerialNumber());
            deviceToSave.setModel(modelService.save(device.getModel()));
            return deviceRepository.save(deviceToSave);
        }
    }
}
