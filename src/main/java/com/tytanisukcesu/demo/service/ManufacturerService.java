package com.tytanisukcesu.demo.service;

import com.tytanisukcesu.demo.dto.ManufacturerDto;
import com.tytanisukcesu.demo.entity.Manufacturer;
import com.tytanisukcesu.demo.repository.ManufacturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ManufacturerService {

    @Autowired
    private ManufacturerRepository manufacturerRepository;


    public ManufacturerDto save(ManufacturerDto manufacturerDto){
        Manufacturer manufacturer = provideEntity(manufacturerDto);
        manufacturerRepository.save(manufacturer);
        return provideDto(manufacturer);
    }

    public boolean remove(Long id){
        Optional<Manufacturer> manufacturer = manufacturerRepository.findById(id);
        if(manufacturer.isPresent()){
            manufacturerRepository.delete(manufacturer.get());
            return true;
        }else{
            return false;
        }
    }

    public List<ManufacturerDto> findAll(){
        List<Manufacturer> manufacturerList = manufacturerRepository.findAll();
        return manufacturerList.stream()
                .map(this::provideDto)
                .collect(Collectors.toList());
    }



    public ManufacturerDto getById(Long id){
        Optional <Manufacturer> manufacturer = manufacturerRepository.findById(id);
        return provideDto(manufacturer.orElse(new Manufacturer()));
    }

    public List<ManufacturerDto> getByName(String name){
        List<Manufacturer> manufacturerList = new ArrayList<>();
        Iterable<Manufacturer> manufacturer = manufacturerRepository.findByName(name);
        while (manufacturer.iterator().hasNext()){
            manufacturerList.add(manufacturer.iterator().next());
            break;
        }
        return manufacturerList.stream()
                .map(this::provideDto)
                .collect(Collectors.toList());
    }



    private ManufacturerDto provideDto(Manufacturer manufacturer) {
        ManufacturerDto manufacturerDto = new ManufacturerDto();
        manufacturerDto.setId(manufacturer.getId());
        manufacturerDto.setName(manufacturer.getName());
        manufacturerDto.setSetOfCopierArticles(manufacturer.getSetOfCopierArticles());
        manufacturerDto.setSetOfCopierModels(manufacturer.getSetOfCopierModels());
        return manufacturerDto;
    }

    private Manufacturer provideEntity(ManufacturerDto manufacturerDto) {
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setId(manufacturerDto.getId());
        manufacturer.setName(manufacturerDto.getName());
        manufacturer.setSetOfCopierArticles(manufacturerDto.getSetOfCopierArticles());
        manufacturer.setSetOfCopierModels(manufacturerDto.getSetOfCopierModels());
        return manufacturer;
    }





}
