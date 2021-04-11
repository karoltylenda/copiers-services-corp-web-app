package com.tytanisukcesu.copiers.controller;

import com.tytanisukcesu.copiers.dto.CustomerDto;
import com.tytanisukcesu.copiers.dto.DeviceDto;
import com.tytanisukcesu.copiers.entity.Device;
import com.tytanisukcesu.copiers.service.DeviceService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/devices")
@RequiredArgsConstructor
public class DeviceController {

    private final DeviceService deviceService;
    private final ModelMapper modelMapper;

    @GetMapping
    public List<DeviceDto> getAll(){
        List<Device> devices = deviceService.findAll();
        return devices.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/search")
    public List<DeviceDto> getByCustomer(@RequestParam Long customerId){
        return deviceService.findByCustomer(customerId)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}")
    public Device getById(@PathVariable("id") Long id){
        return deviceService.findById(id);
    }

    @PostMapping
    public DeviceDto save(@RequestBody DeviceDto deviceDto){
        Device device = convertToEntity(deviceDto);
        Device deviceSaved = deviceService.save(device);
        return convertToDto(deviceSaved);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id){
        if(deviceService.delete(id)){
            return ResponseEntity.accepted().build();
        }else{
            return ResponseEntity.status(404).build();
        }
    }

    @PutMapping(value = "/{id}")
    public DeviceDto update(@PathVariable("id") Long id, @RequestBody DeviceDto deviceDto){
        Device device = convertToEntity(deviceDto);
        Device deviceUpdated = deviceService.update(id,device);
        return convertToDto(deviceUpdated);
    }

    private DeviceDto convertToDto(Device device){
        DeviceDto deviceDto = modelMapper.map(device, DeviceDto.class);
        return deviceDto;
    }

    private Device convertToEntity(DeviceDto deviceDto){
        Device device = modelMapper.map(deviceDto, Device.class);
        return device;
    }

}
