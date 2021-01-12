package com.tytanisukcesu.demo.controller;

import com.tytanisukcesu.demo.dto.DeviceDto;
import com.tytanisukcesu.demo.dto.ManufacturerDto;
import com.tytanisukcesu.demo.dto.ModelDto;
import com.tytanisukcesu.demo.entity.Device;
import com.tytanisukcesu.demo.entity.Manufacturer;
import com.tytanisukcesu.demo.service.DeviceService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/devices")
@RequiredArgsConstructor

public class DeviceController {

    private final DeviceService deviceService;

    @GetMapping
    public List<DeviceDto> getAll(){
        return deviceService.findAll();
    }

    @GetMapping(value = "/{id}")
    public DeviceDto getById(@PathVariable("id") Long id){
        return deviceService.findById(id);
    }

    @PostMapping
    public DeviceDto save(@RequestBody DeviceDto deviceDto){
        return deviceService.save(deviceDto);
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
        return deviceService.update(id,deviceDto);
    }


}
