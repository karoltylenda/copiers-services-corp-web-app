package com.tytanisukcesu.demo.service;

import com.tytanisukcesu.demo.dto.ModelDto;
import com.tytanisukcesu.demo.entity.Manufacturer;
import com.tytanisukcesu.demo.entity.Model;
import com.tytanisukcesu.demo.entity.PrintingFormat;
import com.tytanisukcesu.demo.repository.ManufacturerRepository;
import com.tytanisukcesu.demo.repository.ModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
//@RequiredArgsConstructor
public class ModelService {

    private ModelRepository modelRepository;

    public ModelService(ModelRepository modelRepository, ManufacturerRepository manufacturerRepository) {
        this.modelRepository = modelRepository;
        this.manufacturerRepository = manufacturerRepository;
    }

    private ManufacturerRepository manufacturerRepository;


    private Model provideEntity(ModelDto modelDto) {
        Model model = new Model();
        model.setId(modelDto.getId());
        model.setManufacturer(modelDto.getManufacturer());
        model.setName(modelDto.getName());
        model.setPrintingFormat(modelDto.getPrintingFormat());
        model.setPrintingSpeed(modelDto.getPrintingSpeed());
        model.setPrintsInColor(modelDto.getPrintsInColor());
        model.setProductionYear(modelDto.getProductionYear());
        model.setConsumables(modelDto.getConsumables());
        return model;
    }

    private ModelDto provideDto(Model model) {
        ModelDto modelDto = new ModelDto();
        modelDto.setId(model.getId());
        modelDto.setManufacturer(model.getManufacturer());
        modelDto.setName(model.getName());
        modelDto.setPrintingFormat(model.getPrintingFormat());
        modelDto.setPrintingSpeed(model.getPrintingSpeed());
        modelDto.setPrintsInColor(model.getPrintsInColor());
        modelDto.setProductionYear(model.getProductionYear());
        modelDto.setConsumables(model.getConsumables());
        return modelDto;
    }

    public ModelDto save(ModelDto modelDto) {
        Model model = new Model();
        model.setConsumables(modelDto.getConsumables());
        model.setId(modelDto.getId());
        model.setProductionYear(modelDto.getProductionYear());
        model.setPrintsInColor(modelDto.getPrintsInColor());
        model.setPrintingSpeed(modelDto.getPrintingSpeed());
        model.setPrintingFormat(modelDto.getPrintingFormat());
        model.setName(modelDto.getName());

        modelRepository.save(model);

        return ifManufacturerExists(modelDto, model);
    }

    private ModelDto ifManufacturerExists(ModelDto modelDto, Model model) {
        if (modelDto.getManufacturer() != null) {
            Manufacturer manufacturer = manufacturerRepository.getByName(modelDto.getManufacturer().getName());
            if (manufacturer != null) {
                model.setManufacturer(manufacturer);
                modelRepository.save(model);
                return provideDto(model);
            } else {
                return update(model.getId(), modelDto);
            }
        }
        return new ModelDto();
    }

    public ModelDto update(Long id, ModelDto modelDto) {
        Optional<Model> byId = modelRepository.findById(id);
        if (byId.isPresent()) {
            Model modelUpdated = provideEntity(modelDto);
            Model model = byId.get();
            model.setManufacturer(modelUpdated.getManufacturer());
            model.setName(modelUpdated.getName());
            model.setPrintingFormat(modelUpdated.getPrintingFormat());
            model.setPrintingSpeed(modelUpdated.getPrintingSpeed());
            model.setPrintsInColor(modelUpdated.getPrintsInColor());
            model.setProductionYear(modelUpdated.getProductionYear());
            model.setConsumables(modelUpdated.getConsumables());
            modelRepository.save(model);
            return provideDto(model);
        } else {
            return new ModelDto();
        }
    }

    public boolean delete(Long id) {
        Optional<Model> model = modelRepository.findById(id);
        if (model.isPresent()) {
            modelRepository.delete(model.get());
            return true;
        } else {
            return false;
        }
    }


    public List<ModelDto> findAll() {
        List<Model> models = modelRepository.findAll();
        return models.stream()
                .map(this::provideDto)
                .collect(Collectors.toList());
    }


    public ModelDto getById(Long id) {
        Optional<Model> model = modelRepository.findById(id);
        return provideDto(model.orElse(new Model()));
    }

    public List<ModelDto> getAllByNameContains(String name) {
        List<Model> models = modelRepository.getAllByNameContains(name);
        return models.stream()
                .map(this::provideDto)
                .collect(Collectors.toList());
    }

    public List<ModelDto> getAllByPrintsInColor(boolean isColor) {
        List<Model> models;
        if (isColor) {
            models = modelRepository.getAllByPrintsInColor(isColor);
        } else {
            models = modelRepository.getAllByPrintsInColorNot(isColor);
        }
        return models.stream()
                .map(this::provideDto)
                .collect(Collectors.toList());
    }

    public List<ModelDto> getAllByPrintingSpeedGreaterThanEqual(Integer speed) {
        List<Model> models = modelRepository.getAllByPrintingSpeedGreaterThanEqual(speed);
        return models.stream()
                .map(this::provideDto)
                .collect(Collectors.toList());
    }

    public List<ModelDto> getAllByManufacturer(String manufacturer) {
        List<Model> models = modelRepository.getAllByManufacturerName(manufacturer);
        return models.stream()
                .map(this::provideDto)
                .collect(Collectors.toList());
    }

    public List<ModelDto> getAllByPrintingFormatEquals(PrintingFormat printingFormat) {
        List<Model> models = modelRepository.getAllByPrintingFormatEquals(printingFormat);
        return models.stream()
                .map(this::provideDto)
                .collect(Collectors.toList());
    }

    public List<ModelDto> getAllByParameters(String manufacturerName, String modelName, Boolean printsInColor) {
        List<Model> models = modelRepository.getAllByManufacturerNameAndNameContainsAndPrintsInColor(manufacturerName, modelName, printsInColor);
        return models.stream()
                .map(this::provideDto)
                .collect(Collectors.toList());
    }


}
