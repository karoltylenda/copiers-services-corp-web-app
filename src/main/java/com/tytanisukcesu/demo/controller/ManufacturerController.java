package com.tytanisukcesu.demo.controller;

import com.tytanisukcesu.demo.dto.ManufacturerDto;
import com.tytanisukcesu.demo.entity.Manufacturer;
import com.tytanisukcesu.demo.repository.ManufacturerRepository;
import com.tytanisukcesu.demo.service.ManufacturerService;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.UUID;

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
    public ManufacturerDto getByName(@RequestParam String name){
        return manufacturerService.getById(1L);
    }

    @PostMapping
    public ManufacturerDto save(ManufacturerDto manufacturerDto){
        return manufacturerService.save(manufacturerDto);
    }

    @GetMapping(value = "/{id}")
    public ManufacturerDto getById(@PathVariable("id") Long id){
        return manufacturerService.getById(id);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity delete(@PathParam("id") Long id){
        if(manufacturerService.remove(id)){
            return ResponseEntity.accepted().build();
        }else{
            return ResponseEntity.status(404).build();
        }
    }

}
