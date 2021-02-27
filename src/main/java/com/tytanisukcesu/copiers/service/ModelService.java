package com.tytanisukcesu.copiers.service;

import com.tytanisukcesu.copiers.entity.Model;
import com.tytanisukcesu.copiers.repository.ModelRepository;
import com.tytanisukcesu.copiers.service.exception.ModelNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class ModelService {

    private final ModelRepository modelRepository;
    private final ManufacturerService manufacturerService;
    private static final Logger LOGGER = Logger.getLogger(ModelService.class.getName());

    public List<Model> findAll() {
        List<Model> models = modelRepository.findAll();
        return models;
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
        return modelRepository.findById(id).orElseThrow(()-> new ModelNotFoundException(id,"model"));
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

    public Model update(Long id, Model model) {
        Optional<Model> modelOptional = modelRepository.findById(id);
        if (modelOptional.isPresent()) {
            Model modelUpdated = modelOptional.get();
            modelUpdated.setManufacturer(model.getManufacturer());
            modelUpdated.setName(model.getName());
            modelUpdated.setConsumables(model.getConsumables());
            modelUpdated.setPrintingFormat(model.getPrintingFormat());
            modelUpdated.setPrintingSpeed(model.getPrintingSpeed());
            modelUpdated.setPrintsInColor(model.isPrintsInColor());
            modelUpdated.setProductionYear(model.getProductionYear());
            return modelUpdated;
        } else {
            return new Model();
        }
    }
}
