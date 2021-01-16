package com.tytanisukcesu.demo.controller;

import com.tytanisukcesu.demo.dto.DeviceDto;
import com.tytanisukcesu.demo.dto.ManufacturerDto;
import com.tytanisukcesu.demo.dto.ModelDto;
import com.tytanisukcesu.demo.entity.Device;
import com.tytanisukcesu.demo.entity.Manufacturer;
import com.tytanisukcesu.demo.entity.Model;
import com.tytanisukcesu.demo.service.DeviceService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/devices")
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

    @GetMapping(value = "/{id}")
    public DeviceDto getById(@PathVariable("id") Long id){
        Device device = deviceService.findById(id);
        return convertToDto(device);
    }

    @PostMapping
    public DeviceDto save(@RequestBody DeviceDto deviceDto){
        Device device = convertToEntity(deviceDto);
        Device deviceSaved = deviceService.save(device);
        return convertToDto(deviceSaved);
    }

//    @DeleteMapping(value = "/{id}")
//    public ResponseEntity delete(@PathVariable("id") Long id){
//        if(deviceService.delete(id)){
//            return ResponseEntity.accepted().build();
//        }else{
//            return ResponseEntity.status(404).build();
//        }
//    }
//
//    @PutMapping(value = "/{id}")
//    public DeviceDto update(@PathVariable("id") Long id, @RequestBody DeviceDto deviceDto){
//        return deviceService.update(id,deviceDto);
//    }

    private DeviceDto convertToDto(Device device){
        DeviceDto deviceDto = modelMapper.map(device, DeviceDto.class);
        return deviceDto;
    }

    private Device convertToEntity(DeviceDto deviceDto){
        Device device = modelMapper.map(deviceDto, Device.class);
        return device;
    }
}
