package com.tytanisukcesu.copiers.service;

import com.tytanisukcesu.copiers.dto.CustomerDto;
import com.tytanisukcesu.copiers.dto.DeviceDto;
import com.tytanisukcesu.copiers.entity.Customer;
import com.tytanisukcesu.copiers.entity.Device;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MapperService {

    private final ModelMapper modelMapper;

    public Customer convertToCustomerEntity(CustomerDto customerDto){
        return modelMapper.map(customerDto, Customer.class);
    }

    public CustomerDto convertToCustomerDto(Customer customer){
        return modelMapper.map(customer, CustomerDto.class);
    }

    public Device convertToDeviceEntity(DeviceDto deviceDto){
        return modelMapper.map(deviceDto, Device.class);
    }

    public DeviceDto convertToDeviceDto(Device device){
        return modelMapper.map(device, DeviceDto.class);
    }
}
