package com.tytanisukcesu.copiers.controller;

import com.tytanisukcesu.copiers.dto.CounterDto;
import com.tytanisukcesu.copiers.entity.Counter;
import com.tytanisukcesu.copiers.service.CounterService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/counters")
@RequiredArgsConstructor
public class CounterController {

    private final CounterService counterService;
    private final ModelMapper modelMapper;

    @GetMapping(path = "/searchBy")
    public List<CounterDto> getAllBySerialNumber(@RequestParam String serialNumber){
        List<Counter> counterList = counterService.findAllByDeviceSerialNumber(serialNumber);
        return counterList.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/{id}")
    public CounterDto getById(@PathVariable("id") Long id){
        Counter counter = counterService.findById(id);
        return convertToDto(counter);
    }

    @PostMapping
    public CounterDto save(@RequestBody CounterDto counterDto){
        Counter counter = convertToEntity(counterDto);
        Counter counterSaved = counterService.save(counter);
        return convertToDto(counterSaved);
    }

    private Counter convertToEntity(CounterDto counterDto){
        Counter counter = modelMapper.map(counterDto,Counter.class);
        return counter;
    }

    private CounterDto convertToDto(Counter counter){
        CounterDto counterDto = modelMapper.map(counter,CounterDto.class);
        return counterDto;
    }
}
