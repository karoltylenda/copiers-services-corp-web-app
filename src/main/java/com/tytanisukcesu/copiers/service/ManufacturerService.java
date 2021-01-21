package com.tytanisukcesu.copiers.service;

import com.tytanisukcesu.copiers.entity.Manufacturer;
import com.tytanisukcesu.copiers.repository.ManufacturerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ManufacturerService {

    private final ManufacturerRepository manufacturerRepository;
//
    public List<Manufacturer> findAll() {
        return manufacturerRepository.findAll();
    }

    public Manufacturer findById(Long id) {
        return manufacturerRepository.findById(id).orElse(new Manufacturer());
    }

    public boolean deleteById(Long id) {
        Optional<Manufacturer> manufacturer = manufacturerRepository.findById(id);
        if (manufacturer.isPresent()){
            manufacturerRepository.delete(manufacturer.get());
            return true;
        } else {
            return false;
        }
    }

    public Manufacturer save(Manufacturer manufacturer) {
        Optional<Manufacturer> manufacturerOptional = manufacturerRepository.getManufacturerByName(manufacturer.getName());
        if (manufacturerOptional.isPresent()){
            return manufacturerOptional.get();
        } else {
            Manufacturer manufacturerSaved = manufacturerRepository.save(manufacturer);
            return manufacturerSaved;
        }
    }

    public Manufacturer update(Long id, Manufacturer manufacturer) {
        Manufacturer manufacturerToUpdate = manufacturerRepository.findById(id).orElse(new Manufacturer());
        manufacturerToUpdate.setName(manufacturer.getName());
        manufacturerToUpdate.setArticles(manufacturer.getArticles());
        manufacturerToUpdate.setModels(manufacturer.getModels());
        Manufacturer manufacturerUpdated = manufacturerRepository.save(manufacturerToUpdate);
        return manufacturerUpdated;
    }

    public List<Manufacturer> findAllByName(String name) {
        return manufacturerRepository.getAllByNameContains(name);
    }
}
