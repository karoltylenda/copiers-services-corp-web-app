package com.tytanisukcesu.demo.service;

import com.tytanisukcesu.demo.entity.Model;
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

    public List<Model> findAll() {
        return modelRepository.findAll();
    }

    public Model save(Model model){
        Optional<Model> modelOptional = modelRepository.getByNameContains(model.getName());
        if (modelOptional.isPresent() && modelOptional.get().getManufacturer().getName().equalsIgnoreCase(model.getManufacturer().getName())){
            return modelOptional.get();
        } else {
            Model modelSaved = modelRepository.save(model);
            return modelSaved;
        }
    }

    public Model findById(Long id) {
        Optional<Model> modelOptional = modelRepository.findById(id);
        return modelOptional.orElse(new Model());
    }
}
