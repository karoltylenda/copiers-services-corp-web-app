package com.tytanisukcesu.demo.service;

import com.tytanisukcesu.demo.dto.DeviceDto;
import com.tytanisukcesu.demo.entity.Device;
import com.tytanisukcesu.demo.repository.DeviceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DeviceService {

    private final DeviceRepository deviceRepository;

    public List<DeviceDto> findAll(){
        return deviceRepository.findAll()
                .stream()
                .map(this::provideDto)
                .collect(Collectors.toList());
    }

    public DeviceDto save(DeviceDto deviceDto){
        Device device = provideEntity(deviceDto);
        deviceRepository.save(device);
        return provideDto(device);
    }

    private DeviceDto provideDto(Device device){
        return DeviceDto.builder()
                .id(device.getId())
                .apartmentNumber(device.getApartmentNumber())
                .city(device.getCity())
                .houseNumber(device.getHouseNumber())
                .postCode(device.getPostCode())
                .colorPagePrice(device.getColorPagePrice())
                .monoPagePrice(device.getMonoPagePrice())
                .serialNumber(device.getSerialNumber())
                .build();
    }

    private Device provideEntity(DeviceDto deviceDto){
        return Device.builder()
                .id(deviceDto.getId())
                .apartmentNumber(deviceDto.getApartmentNumber())
                .city(deviceDto.getCity())
                .houseNumber(deviceDto.getHouseNumber())
                .postCode(deviceDto.getPostCode())
                .colorPagePrice(deviceDto.getColorPagePrice())
                .monoPagePrice(deviceDto.getMonoPagePrice())
                .serialNumber(deviceDto.getSerialNumber())
                .build();
    }
}
