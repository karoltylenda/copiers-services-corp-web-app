package com.tytanisukcesu.demo.service;

import com.tytanisukcesu.demo.dto.ModelDto;
import com.tytanisukcesu.demo.entity.Manufacturer;
import com.tytanisukcesu.demo.entity.Model;
import com.tytanisukcesu.demo.repository.ManufacturerRepository;
import com.tytanisukcesu.demo.repository.ModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ModelService {

    private final ModelRepository modelRepository;
    private final ManufacturerRepository manufacturerRepository;
    private final ManufacturerService manufacturerService;

    public Model provideEntity(ModelDto modelDto) {
        Model model = new Model();
        model.setId(modelDto.getId());
        model.setManufacturer(modelDto.getManufacturer());
        model.setName(modelDto.getName());
        model.setPrintingFormat(modelDto.getPrintingFormat());
        model.setPrintingSpeed(modelDto.getPrintingSpeed());
        model.setPrintsInColor(modelDto.isPrintsInColor());
        model.setProductionYear(modelDto.getProductionYear());
        model.setConsumables(modelDto.getConsumables());
        return model;
    }

    public ModelDto provideDto(Model model) {
        ModelDto modelDto = new ModelDto();
        modelDto.setId(model.getId());
        modelDto.setManufacturer(model.getManufacturer());
        modelDto.setName(model.getName());
        modelDto.setPrintingFormat(model.getPrintingFormat());
        modelDto.setPrintingSpeed(model.getPrintingSpeed());
        modelDto.setPrintsInColor(model.isPrintsInColor());
        modelDto.setProductionYear(model.getProductionYear());
        modelDto.setConsumables(model.getConsumables());
        return modelDto;
    }

    public ModelDto save(ModelDto modelDto) {
        Model model = provideEntity(modelDto);
        List<Model> modelList = modelRepository.getAllByNameAndManufacturerName(model.getName(), model.getManufacturer().getName());
        if (modelList.isEmpty()) {
            model = modelRepository.save(ifManufacturerExists(modelDto));
        }
        return provideDto(model);
    }

    private Model ifManufacturerExists(ModelDto modelDto) {
        Manufacturer manufacturer = modelDto.getManufacturer();
        if (manufacturerRepository.getByName(manufacturer.getName()) == null) {
            manufacturer = manufacturerRepository.save(manufacturer);
        } else {
            manufacturer = manufacturerRepository.getByName(manufacturer.getName());
        }
        modelDto.setManufacturer(manufacturer);
        return provideEntity(modelDto);
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
            model.setPrintsInColor(modelUpdated.isPrintsInColor());
            model.setProductionYear(modelUpdated.getProductionYear());
            model.setConsumables(modelUpdated.getConsumables());
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

    public List<ModelDto> getAllByParameters(String manufacturerName, String modelName, boolean printsInColor) {
        List<Model> models = modelRepository.getAllByManufacturerNameContainsAndNameContainsAndPrintsInColor(manufacturerName, modelName, printsInColor);
        return models.stream()
                .map(this::provideDto)
                .collect(Collectors.toList());
    }

    public List<ModelDto> getAllByParameters(String manufacturerName, String modelName) {
        List<Model> models = modelRepository.getAllByManufacturerNameContainsAndNameContains(manufacturerName, modelName);
        return models.stream()
                .map(this::provideDto)
                .collect(Collectors.toList());
    }
}
