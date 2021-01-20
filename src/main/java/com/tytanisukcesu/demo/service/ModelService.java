package com.tytanisukcesu.demo.service;

import com.tytanisukcesu.demo.dto.ModelDto;
import com.tytanisukcesu.demo.entity.Model;
import com.tytanisukcesu.demo.repository.ModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ModelService {

    private final ModelRepository modelRepository;
    private final ManufacturerService manufacturerService;

    public List<Model> findAll() {
        return modelRepository.findAll();
    }

    public Model save(Model model) {
        Optional<Model> modelOptional = modelRepository.getModelByNameAndManufacturerName(model.getName(), model.getManufacturer().getName());
        if (modelOptional.isPresent()) {
            return modelOptional.get();
        } else {
            model.setManufacturer(manufacturerService.save(model.getManufacturer()));
            Model modelSaved = modelRepository.save(model);
            return modelSaved;
        }
    }

    public Model findById(Long id) {
        Optional<Model> modelOptional = modelRepository.findById(id);
        return modelOptional.orElse(new Model());
    }

    public List<ModelDto> getAllByParameters(String manufacturer, String model, boolean printsInColor) {
        return null;
    }

    public boolean delete(Long id) {
        return true;
    }

    public ModelDto update(Long id, ModelDto modelDto) {
        return null;
    }
}
