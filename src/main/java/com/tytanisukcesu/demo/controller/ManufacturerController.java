package com.tytanisukcesu.demo.controller;

import com.tytanisukcesu.demo.dto.ManufacturerDto;
import com.tytanisukcesu.demo.service.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/manufacturers")
public class ManufacturerController {

    @Autowired
    private ManufacturerService manufacturerService;

    @GetMapping(value = "all")
    public List<ManufacturerDto> getAll(){
        return manufacturerService.findAll();
    }

    @GetMapping
    public List<ManufacturerDto> getByName(@RequestParam String name){
        return manufacturerService.getByName(name);
    }

    @PostMapping
    public ManufacturerDto save(@RequestBody ManufacturerDto manufacturerDto){
        return manufacturerService.save(manufacturerDto);
    }

    @GetMapping(value = "/{id}")
    public ManufacturerDto getById(@PathVariable("id") Long id){
        return manufacturerService.getById(id);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id){
        if(manufacturerService.delete(id)){
            return ResponseEntity.accepted().build();
        }else{
            return ResponseEntity.status(404).build();
        }
    }

    @PutMapping(value = "/{id}")
    public ManufacturerDto update(@PathVariable("id") Long id, @RequestBody ManufacturerDto manufacturerDto){
        return manufacturerService.update(id,manufacturerDto);
    }

}
