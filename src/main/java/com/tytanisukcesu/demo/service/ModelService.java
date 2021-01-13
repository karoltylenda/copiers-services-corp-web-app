package com.tytanisukcesu.demo.service;

import com.tytanisukcesu.demo.entity.Manufacturer;
import com.tytanisukcesu.demo.entity.Model;
import com.tytanisukcesu.demo.repository.ManufacturerRepository;
import com.tytanisukcesu.demo.repository.ModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ModelService {

    private final ModelRepository modelRepository;
    private final ManufacturerRepository manufacturerRepository;

    public List<Model> findAll() {
        return modelRepository.findAll();
    }


    public Model save(Model model) {
        Optional<Model> modelOptional = modelRepository.getAllByNameSerialNumber(model.getSerialNumber());
        if (modelOptional.isPresent()) {
            return modelOptional.get();
        } else {
            ifManufacturerExists(model);
            return modelRepository.save(model);
        }
    }


    private Model ifManufacturerExists(Model model) {
        Manufacturer manufacturer = model.getManufacturer();
        if (manufacturerRepository.getByName(manufacturer.getName()).isEmpty()) {
            manufacturer = manufacturerRepository.save(manufacturer);
        } else {
            manufacturer = manufacturerRepository.getByName(manufacturer.getName()).get();
        }
        model.setManufacturer(manufacturer);
        return model;
    }

    //bez id i serialnumber
    public Model update(Long id, Model model) {
        Model modelToUpdate = modelRepository.findById(id).orElse(new Model());
        modelToUpdate.setManufacturer(model.getManufacturer());
        modelToUpdate.setName(model.getName());
        modelToUpdate.setPrintingFormat(model.getPrintingFormat());
        modelToUpdate.setPrintingSpeed(model.getPrintingSpeed());
        modelToUpdate.setProductionYear(model.getProductionYear());
        modelToUpdate.setColorPagePrice(model.getColorPagePrice());
        modelToUpdate.setCounters(model.getCounters());
        modelToUpdate.setMonoPagePrice(model.getMonoPagePrice());
        modelToUpdate.setPrintsInColor(model.isPrintsInColor());
        return modelToUpdate;
    }

    public boolean deleteById(Long id) {
        Optional<Model> model = modelRepository.findById(id);
        if (model.isPresent()) {
            modelRepository.delete(model.get());
            return true;
        } else {
            return false;
        }
    }

    public Model findById(Long id) {
        Optional<Model> modelOptional = modelRepository.findById(id);
        return modelOptional.orElse(new Model());
    }


    public List<Model> getAllByParameters(String manufacturerName, String modelName, boolean printsInColor) {
        return modelRepository.getAllByManufacturerNameContainsAndNameContainsAndPrintsInColor(manufacturerName, modelName, printsInColor);
    }

    public List<Model> getAllByParameters(String manufacturerName, String modelName) {
        return modelRepository.getAllByManufacturerNameContainsAndNameContains(manufacturerName, modelName);
    }

}

