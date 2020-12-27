package com.tytanisukcesu.demo.controller;

import com.tytanisukcesu.demo.dto.ManufacturerDto;
import com.tytanisukcesu.demo.service.ManufacturerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/manufacturers")
//@RequiredArgsConstructor
public class ManufacturerController {

    public ManufacturerController(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }

    private ManufacturerService manufacturerService;

    //TODO z 2 parametrami
    @GetMapping(value = "/search")
    public List<ManufacturerDto> getByName(@RequestParam String name) {
            return manufacturerService.getByName(name);
        }


    @GetMapping
    public List<ManufacturerDto> getAll(){
        return manufacturerService.findAll();
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
