package com.tytanisukcesu.demo.service;

import com.tytanisukcesu.demo.dto.ModelDto;
import com.tytanisukcesu.demo.entity.Model;
import com.tytanisukcesu.demo.repository.ModelRepository;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ModelService {

    @Autowired
    private ModelRepository modelRepository;

    private Model provideEntity(ModelDto modelDto){
        Model model = new Model();
        model.setManufacturer(modelDto.getManufacturer());
        model.setName(modelDto.getName());
        model.setPrintingFormat(modelDto.getPrintingFormat());
        model.setPrintingSpeed(modelDto.getPrintingSpeed());
        model.setPrintsInColor(modelDto.getPrintsInColor());
        model.setProductionYear(modelDto.getProductionYear());
        model.setSetOfConsumables(modelDto.getSetOfConsumables());
        return model;
    }

    private ModelDto provideDto(Model model){
        ModelDto modelDto = new ModelDto();
        modelDto.setManufacturer(model.getManufacturer());
        modelDto.setName(model.getName());
        modelDto.setPrintingFormat(model.getPrintingFormat());
        modelDto.setPrintingSpeed(model.getPrintingSpeed());
        modelDto.setPrintsInColor(model.getPrintsInColor());
        modelDto.setProductionYear(model.getProductionYear());
        modelDto.setSetOfConsumables(model.getSetOfConsumables());
        return modelDto;
    }

    public ModelDto save(ModelDto modelDto){
        Model model = provideEntity(modelDto);
        modelRepository.save(model);
        return provideDto(model);
    }

    public boolean delete(Long id){
        Optional<Model> model = modelRepository.findById(id);
        if(model.isPresent()){
            modelRepository.delete(model.get());
            return true;
        }else{
            return false;
        }
    }

    public List<ModelDto> findAll(){
        List<Model> models = modelRepository.findAll();
        return models.stream()
                .map(this::provideDto)
                .collect(Collectors.toList());
    }

    public ModelDto update(Long id, ModelDto modelDto){
        Model model = modelRepository.findById(id).get();
        Model modelUpdated = provideEntity(modelDto);
        model.setManufacturer(modelUpdated.getManufacturer());
        model.setName(modelUpdated.getName());
        model.setPrintingFormat(modelUpdated.getPrintingFormat());
        model.setPrintingSpeed(modelUpdated.getPrintingSpeed());
        model.setPrintsInColor(modelUpdated.getPrintsInColor());
        model.setProductionYear(modelUpdated.getProductionYear());
        model.setSetOfConsumables(modelUpdated.getSetOfConsumables());
        modelRepository.save(model);
        return provideDto(model);
    }

    public ModelDto getById(Long id){
        Optional<Model> model = modelRepository.findById(id);
        return provideDto(model.orElse(new Model()));
    }

    public List<ModelDto> getAllByNameContains(String name){
        List<Model> models = modelRepository.getAllByNameContains(name);
        return models.stream()
                .map(this::provideDto)
                .collect(Collectors.toList());
    }

    public List<ModelDto> getAllByPrintsInColor(){
        List<Model> models = modelRepository.getAllByPrintsInColor();
        return models.stream()
                .map(this::provideDto)
                .collect(Collectors.toList());
    }

    public List<ModelDto> getAllByPrintingSpeedGreaterThanEqual(Integer speed){
        List<Model> models = modelRepository.getAllByPrintingSpeedGreaterThanEqual(speed);
        return models.stream()
                .map(this::provideDto)
                .collect(Collectors.toList());
    }

    public List<ModelDto> getAllByManufacturer(String manufacturer){
        List<Model> models = modelRepository.getAllByManufacturer(manufacturer);
        return models.stream()
                .map(this::provideDto)
                .collect(Collectors.toList());
    }

    public List<ModelDto> getAllByPrintingFormatEquals(String printingFormat){
        List<Model> models = modelRepository.getAllByPrintingFormatEquals(printingFormat);
        return models.stream()
                .map(this::provideDto)
                .collect(Collectors.toList());
    }







}
