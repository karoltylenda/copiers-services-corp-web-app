package com.tytanisukcesu.demo.controller;

import com.tytanisukcesu.demo.dto.ModelDto;
import com.tytanisukcesu.demo.entity.Manufacturer;
import com.tytanisukcesu.demo.service.ModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/models")
@RequiredArgsConstructor
public class ModelController {

    private final ModelService modelService;

    @GetMapping(value = "/search")
    public List<ModelDto> getByAllParameters(@RequestParam(required = false, defaultValue = "") String manufacturer,
                                             @RequestParam(required = false, defaultValue = "") String model,
                                             @RequestParam(defaultValue = "true") boolean printsInColor){
        return modelService.getAllByParameters(manufacturer,model,printsInColor);
    }

    @GetMapping
    public List<ModelDto> getAll(){
        return modelService.findAll();
    }

    @PostMapping
    public ModelDto save(@RequestBody ModelDto modelDto){
        return modelService.save(modelDto);
    }

    @GetMapping(value = "/{id}")
    public ModelDto getById(@PathVariable("id") Long id){
        return modelService.getById(id);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id){
        if(modelService.delete(id)){
            return ResponseEntity.accepted().build();
        }else{
            return ResponseEntity.status(404).build();
        }
    }

    @PutMapping(value = "/{id}")
    public ModelDto update(@PathVariable("id") Long id,@RequestBody ModelDto modelDto){
        return modelService.update(id,modelDto);
    }



}
