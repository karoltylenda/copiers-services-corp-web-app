package com.tytanisukcesu.copiers.controller;

import com.tytanisukcesu.copiers.entity.Counter;
import com.tytanisukcesu.copiers.service.CounterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/counters")
@RequiredArgsConstructor
public class CounterController {

    private final CounterService counterService;

    @GetMapping(value = "/{serialNumber}")
    public Counter getAll(@PathVariable("serialNumber") String serialNumber){
        return counterService.findAllByDeviceSerialNumber(serialNumber);
    }

}
