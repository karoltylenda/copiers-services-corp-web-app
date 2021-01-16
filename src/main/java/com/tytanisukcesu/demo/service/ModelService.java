package com.tytanisukcesu.demo.service;

import com.tytanisukcesu.demo.entity.Manufacturer;
import com.tytanisukcesu.demo.entity.Model;
import com.tytanisukcesu.demo.repository.ManufacturerRepository;
import com.tytanisukcesu.demo.repository.ModelRepository;
import lombok.RequiredArgsConstructor;
import org.dom4j.rule.Mode;
import org.springframework.stereotype.Service;

import javax.crypto.spec.OAEPParameterSpec;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ModelService {

    private final ModelRepository modelRepository;
    private final ManufacturerRepository manufacturerRepository;
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
}
