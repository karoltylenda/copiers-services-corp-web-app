package com.tytanisukcesu.copiers.controller;

import com.tytanisukcesu.copiers.dto.AddressDto;
import com.tytanisukcesu.copiers.dto.DeviceDto;
import com.tytanisukcesu.copiers.entity.Address;
import com.tytanisukcesu.copiers.entity.Device;
import com.tytanisukcesu.copiers.entity.Model;
import com.tytanisukcesu.copiers.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/addresses")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @GetMapping
    public List<AddressDto> getAll(){
        return addressService.findAll();
    }

    @GetMapping(value = "/{id}")
    public AddressDto getById(@PathVariable("id") Long id){
        return addressService.findById(id);
    }
}
