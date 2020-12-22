package com.tytanisukcesu.demo.controller;

import com.tytanisukcesu.demo.dto.ModelDto;
import com.tytanisukcesu.demo.entity.Manufacturer;
import com.tytanisukcesu.demo.entity.Model;
import com.tytanisukcesu.demo.entity.PrintingFormat;
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


//    @GetMapping(value = "/search")
//    public List<ModelDto> getByParameter(@RequestParam String name,
//                                         @RequestParam String manufacturer,
//                                         @RequestParam PrintingFormat printingFormat,
//                                         @RequestParam Integer speed,
//                                         @RequestParam Boolean printInColor){
//        if(name!=null){
//            return modelService.getAllByNameContains(name);
//        }else if(manufacturer!=null){
//            return modelService.getAllByManufacturer(manufacturer);
//        }else if(printingFormat!=null){
//            return modelService.getAllByPrintingFormatEquals(printingFormat);
//        }else if(speed!=null){
//            return modelService.getAllByPrintingSpeedGreaterThanEqual(speed);
//        }else if(printInColor!=null){
//            return modelService.getAllByPrintsInColor(printInColor);
//        }
//    }

    @GetMapping(value = "/search")
    public List<ModelDto> getByAllParameters(@RequestParam Manufacturer manufacturer,
                                             @RequestParam String name,
                                             @RequestParam Boolean printsInColor){
        return modelService.getAllByParameters(manufacturer,name,printsInColor);
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
