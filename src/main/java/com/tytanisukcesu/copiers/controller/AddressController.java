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
    private final ModelMapper modelMapper;

    @GetMapping
    public List<AddressDto> getAll(){
        List<Address> devices = addressService.findAll();
        return devices.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}")
    public AddressDto getById(@PathVariable("id") Long id){
        Address address = addressService.findById(id);
        return convertToDto(address);
    }

    private AddressDto convertToDto(Address address){
        AddressDto addressDto = modelMapper.map(address, AddressDto.class);
        return addressDto;
    }

    private Address convertToEntity(AddressDto addressDto){
        Address address = modelMapper.map(addressDto, Address.class);
        return address;
    }

}
