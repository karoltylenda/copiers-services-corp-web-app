package com.tytanisukcesu.copiers.service;

import com.tytanisukcesu.copiers.entity.Device;
import com.tytanisukcesu.copiers.entity.Model;
import com.tytanisukcesu.copiers.repository.DeviceRepository;
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
        if (deviceOptional.isPresent()) {
            return deviceOptional.get();
        } else {
            Device deviceToSave = new Device();
            deviceToSave.setSerialNumber(device.getSerialNumber());
            deviceToSave.setModel(modelService.save(device.getModel()));
            return deviceRepository.save(deviceToSave);
        }
    }

    @Transactional
    public Device update(Long id, Device device) {
        Optional<Device> deviceOptional = deviceRepository.findById(id);
        if (deviceOptional.isPresent()) {
            Device deviceUpdated = deviceOptional.get();
            deviceUpdated.setModel(device.getModel());
            deviceUpdated.setSerialNumber(device.getSerialNumber());
            deviceUpdated.setAddress(device.getAddress());
            deviceUpdated.setCounters(device.getCounters());
            deviceUpdated.setCustomer(device.getCustomer());
            deviceUpdated.setColorPagePrice(device.getColorPagePrice());
            deviceUpdated.setLeasePrice(device.getLeasePrice());
            deviceUpdated.setMonoPagePrice(device.getMonoPagePrice());
            return deviceUpdated;
        } else {
            return new Device();
        }
    }

    public boolean delete(Long id) {
        Optional<Device> deviceOptional = deviceRepository.findById(id);
        if (deviceOptional.isPresent()) {
            deviceRepository.delete(deviceOptional.get());
            return true;
        } else {
            return false;
        }
    }
}
