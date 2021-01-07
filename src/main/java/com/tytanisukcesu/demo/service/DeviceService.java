package com.tytanisukcesu.demo.service;

import com.tytanisukcesu.demo.dto.DeviceDto;
import com.tytanisukcesu.demo.entity.Device;
import com.tytanisukcesu.demo.repository.DeviceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DeviceService {

    private final DeviceRepository deviceRepository;

    public List<DeviceDto> findAll() {
        return deviceRepository.findAll()
                .stream()
                .map(this::provideDto)
                .collect(Collectors.toList());
    }

    public DeviceDto save(DeviceDto deviceDto) {
        Device device = provideEntity(deviceDto);
        deviceRepository.save(device);
        return provideDto(device);
    }

    public DeviceDto findById(Long id) {
        Optional<Device> device = deviceRepository.findById(id);
        return provideDto(device.orElse(new Device()));
    }

    public DeviceDto update(Long id, DeviceDto deviceDto) {
        Device device = deviceRepository.findById(id).orElseThrow();
        Device deviceUpdated = provideEntity(deviceDto);
        device.setColorPagePrice(deviceUpdated.getColorPagePrice());
        device.setCounters(deviceUpdated.getCounters());
        device.setCustomer(deviceUpdated.getCustomer());
        device.setId(deviceUpdated.getId());
        device.setModel(deviceUpdated.getModel());
        device.setMonoPagePrice(deviceUpdated.getMonoPagePrice());
        device.setSerialNumber(deviceUpdated.getSerialNumber());
        return provideDto(device);
    }

    public boolean delete(Long id) {
        Optional<Device> device = deviceRepository.findById(id);
        if (device.isPresent()) {
            deviceRepository.delete(device.get());
            return true;
        } else {
            return false;
        }
    }

    //TODO
    //metode do szukania po serial number i customer dla device


    private DeviceDto provideDto(Device device) {
        return DeviceDto.builder()
                .id(device.getId())
                .colorPagePrice(device.getColorPagePrice())
                .monoPagePrice(device.getMonoPagePrice())
                .serialNumber(device.getSerialNumber())
                .model(device.getModel())
                .counters(device.getCounters())
                .customer(device.getCustomer())
                .build();
    }

    private Device provideEntity(DeviceDto deviceDto) {
        return Device.builder()
                .id(deviceDto.getId())
                .colorPagePrice(deviceDto.getColorPagePrice())
                .monoPagePrice(deviceDto.getMonoPagePrice())
                .serialNumber(deviceDto.getSerialNumber())
                .counters(deviceDto.getCounters())
                .customer(deviceDto.getCustomer())
                .model(deviceDto.getModel())
                .build();
    }
}
