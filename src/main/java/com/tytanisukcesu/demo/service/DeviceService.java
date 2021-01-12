package com.tytanisukcesu.demo.service;

import com.tytanisukcesu.demo.dto.DeviceDto;
import com.tytanisukcesu.demo.dto.ManufacturerDto;
import com.tytanisukcesu.demo.dto.ModelDto;
import com.tytanisukcesu.demo.entity.Device;
import com.tytanisukcesu.demo.entity.Manufacturer;
import com.tytanisukcesu.demo.entity.Model;
import com.tytanisukcesu.demo.repository.DeviceRepository;
import com.tytanisukcesu.demo.repository.ManufacturerRepository;
import com.tytanisukcesu.demo.repository.ModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DeviceService {

    private final DeviceRepository deviceRepository;
    private final ManufacturerRepository manufacturerRepository;
    private final ManufacturerService manufacturerService;
    private final ModelService modelService;
    private final ModelRepository modelRepository;

    public List<DeviceDto> findAll() {
        return deviceRepository.findAll()
                .stream()
                .map(this::provideDto)
                .collect(Collectors.toList());
    }

    //dziala ale nie dodaje duplikatow
//    public DeviceDto save(DeviceDto deviceDto) {
//        Device device = provideEntity(deviceDto);
////        Model model = device.getModel();
////        ModelDto modelDto = modelService.provideDto(model);
////        modelDto = modelService.save(modelDto);
////        model = modelService.provideEntity(modelDto);
////        device.setModel(model);
//        deviceRepository.save(device);
////        modelService.update(model.getId(),modelDto);
//        return provideDto(device);
//    }

    public DeviceDto save(DeviceDto deviceDto) {
        Device device = provideEntity(deviceDto);

        Model model = device.getModel();
        ModelDto modelDto = modelService.provideDto(model);
        List<Model> models = modelRepository.getAllByNameAndManufacturerName(model.getName(),model.getManufacturer().getName());
        if(models.isEmpty()){
            modelDto = modelService.save(modelDto);
        }else{
            modelDto = modelService.provideDto(models.get(0));
        }

        Model modelFound = modelService.provideEntity(modelDto);
        Set<Device> devices = modelFound.getDevices();
        devices.add(device);

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
