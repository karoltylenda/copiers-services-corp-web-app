package com.tytanisukcesu.copiers.controller;

import com.tytanisukcesu.copiers.dto.ManufacturerDto;
import com.tytanisukcesu.copiers.entity.Manufacturer;
import com.tytanisukcesu.copiers.service.ManufacturerService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/manufacturers")
@RequiredArgsConstructor
public class ManufacturerController {

    private final ManufacturerService manufacturerService;
    private final ModelMapper modelMapper;

    @GetMapping(value = "/search")
    public List<ManufacturerDto> getByName(@RequestParam String name) {
        List<Manufacturer> manufacturers = manufacturerService.findAllByName(name);
        return manufacturers.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping
    public List<ManufacturerDto> getAll() {
        List<Manufacturer> manufacturers = manufacturerService.findAll();
        return manufacturers.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public ManufacturerDto save(@RequestBody ManufacturerDto manufacturerDto) {
        Manufacturer manufacturer = convertToEntity(manufacturerDto);
        Manufacturer manufacturerSaved = manufacturerService.save(manufacturer);
        return convertToDto(manufacturerSaved);
    }

    @GetMapping(value = "/{id}")
    public ManufacturerDto getById(@PathVariable("id") Long id) {
        Manufacturer manufacturer = manufacturerService.findById(id);
        return convertToDto(manufacturer);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        if (manufacturerService.deleteById(id)) {
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    @PutMapping(value = "/{id}")
    public ManufacturerDto update(@PathVariable("id") Long id, @RequestBody ManufacturerDto manufacturerDto) {
        Manufacturer manufacturer  = convertToEntity(manufacturerDto);
        Manufacturer manufacturerUpdated = manufacturerService.update(id, manufacturer);
        return convertToDto(manufacturerUpdated);
    }

    private ManufacturerDto convertToDto(Manufacturer manufacturer){
        ManufacturerDto manufacturerDto = modelMapper.map(manufacturer, ManufacturerDto.class);
        return manufacturerDto;
    }

    private Manufacturer convertToEntity(ManufacturerDto manufacturerDto){
        Manufacturer manufacturer = modelMapper.map(manufacturerDto, Manufacturer.class);
        return manufacturer;
    }

}
