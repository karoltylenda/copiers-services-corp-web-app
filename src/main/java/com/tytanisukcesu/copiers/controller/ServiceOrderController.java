package com.tytanisukcesu.copiers.controller;

import com.tytanisukcesu.copiers.dto.ServiceOrderDto;
import com.tytanisukcesu.copiers.entity.ServiceOrder;
import com.tytanisukcesu.copiers.service.ServiceOrderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/serviceOrders")
@RequiredArgsConstructor
public class ServiceOrderController {

    private final ServiceOrderService serviceOrderService;
    private final ModelMapper modelMapper;

    @GetMapping
    public List<ServiceOrderDto> getAll() {
        List<ServiceOrder> serviceOrders = serviceOrderService.findAll();
        return serviceOrders.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public ServiceOrderDto save(@RequestBody ServiceOrderDto serviceOrderDto) {
        ServiceOrder serviceOrder = convertToEntity(serviceOrderDto);
        ServiceOrder serviceOrderSaved = serviceOrderService.save(serviceOrder);
        return convertToDto(serviceOrderSaved);
    }

    @GetMapping(value = "/{id}")
    public ServiceOrderDto findById(@PathVariable("id") Long id) {
        ServiceOrder serviceOrder = serviceOrderService.findById(id);
        return convertToDto(serviceOrder);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        if (serviceOrderService.delete(id)) {
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    @PutMapping(value = "/{id}")
    public ServiceOrderDto update(@PathVariable("id") Long id, @RequestBody ServiceOrderDto serviceOrderDto) {
        ServiceOrder serviceOrder = convertToEntity(serviceOrderDto);
        ServiceOrder serviceOrderUpdated = serviceOrderService.update(id, serviceOrder);
        return convertToDto(serviceOrderUpdated);
    }


    private ServiceOrderDto convertToDto(ServiceOrder serviceOrder) {
        ServiceOrderDto serviceOrderDto = modelMapper.map(serviceOrder, ServiceOrderDto.class);
        return serviceOrderDto;
    }

    private ServiceOrder convertToEntity(ServiceOrderDto serviceOrderDto) {
        ServiceOrder serviceOrder = modelMapper.map(serviceOrderDto, ServiceOrder.class);
        return serviceOrder;
    }

}
