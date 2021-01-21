package com.tytanisukcesu.copiers.service;

import com.tytanisukcesu.copiers.dto.ModelDto;
import com.tytanisukcesu.copiers.entity.Model;
import com.tytanisukcesu.copiers.repository.ModelRepository;
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
        return modelRepository.findById(id).orElse(new Model());
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
