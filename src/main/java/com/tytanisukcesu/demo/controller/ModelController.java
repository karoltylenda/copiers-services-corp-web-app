package com.tytanisukcesu.demo.controller;

import com.tytanisukcesu.demo.dto.ModelDto;
import com.tytanisukcesu.demo.entity.Manufacturer;
import com.tytanisukcesu.demo.entity.Model;
import com.tytanisukcesu.demo.service.ModelService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/models")
@RequiredArgsConstructor
public class ModelController {

    private final ModelService modelService;
    private final ModelMapper modelMapper;


    //    @GetMapping(value = "/search")
//    public List<ModelDto> getByAllParameters(@RequestParam(required = false, defaultValue = "") String manufacturer,
//                                             @RequestParam(required = false, defaultValue = "") String model,
//                                             @RequestParam(defaultValue = "true") boolean printsInColor){
//        return modelService.getAllByParameters(manufacturer,model,printsInColor);
//    }
//

    @GetMapping(value = "/search")
    public List<Model> 

    @GetMapping
    public List<ModelDto> getAll() {
        List<Model> models = modelService.findAll();
        return models.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public ModelDto save(@RequestBody ModelDto modelDto) {
        Model model = convertToEntity(modelDto);
        Model modelSaved = modelService.save(model);
        return convertToDto(modelSaved);
    }

    @GetMapping(value = "/{id}")
    public ModelDto getById(@PathVariable("id") Long id) {
        Model model = modelService.findById(id);
        return convertToDto(model);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        if (modelService.deleteById(id)) {
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    @PutMapping(value = "/{id}")
    public ModelDto update(@PathVariable("id") Long id, @RequestBody ModelDto modelDto) {
        Model model = convertToEntity(modelDto);
        Model modelUpdated = modelService.update(id, model);
        return convertToDto(modelUpdated);
    }

    private ModelDto convertToDto(Model model) {
        ModelDto modelDto = modelMapper.map(model, ModelDto.class);
        return modelDto;
    }

    private Model convertToEntity(ModelDto modelDto) {
        Model model = modelMapper.map(modelDto, Model.class);
        return model;
    }
}
