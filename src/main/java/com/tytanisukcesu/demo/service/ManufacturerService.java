package com.tytanisukcesu.demo.service;

import com.tytanisukcesu.demo.dto.ManufacturerDto;
import com.tytanisukcesu.demo.entity.Manufacturer;
import com.tytanisukcesu.demo.repository.ManufacturerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.tytanisukcesu.demo.mapper.ManufacturerMapper.MAPPER;

@Service
@RequiredArgsConstructor
public class ManufacturerService {

    private final ManufacturerRepository manufacturerRepository;

    public ManufacturerDto save(ManufacturerDto manufacturerDto) {
        Manufacturer manufacturer = provideEntity(manufacturerDto);
        manufacturerRepository.save(manufacturer);
        return provideDto(manufacturer);
    }

    public boolean delete(Long id) {
        Optional<Manufacturer> manufacturer = manufacturerRepository.findById(id);
        if (manufacturer.isPresent()) {
            manufacturerRepository.delete(manufacturer.get());
            return true;
        } else {
            return false;
        }
    }

    public Optional<Manufacturer> findManufacturerById(Long id) {
        return manufacturerRepository.findById(id);
    }

    public List<ManufacturerDto> findAll() {
        List<Manufacturer> manufacturerList = manufacturerRepository.findAll();
        return manufacturerList.stream()
                .map(this::provideDto)
                .collect(Collectors.toList());
    }

    public ManufacturerDto update(Long id, ManufacturerDto manufacturerDto) {
        Manufacturer manufacturer = manufacturerRepository.findById(id).orElseThrow(); //tutaj zamiast .Get
        Manufacturer manufacturerUpdated = provideEntity(manufacturerDto);
        manufacturer.setName(manufacturerUpdated.getName());
        manufacturer.setSetOfCopierModels(manufacturerUpdated.getSetOfCopierModels());
        manufacturer.setSetOfCopierArticles(manufacturerUpdated.getSetOfCopierArticles());
        manufacturerRepository.save(manufacturer);
        return provideDto(manufacturer);
    }


    public ManufacturerDto getById(Long id) {
        Optional<Manufacturer> manufacturer = manufacturerRepository.findById(id);
        return provideDto(manufacturer.orElse(new Manufacturer()));
    }

    public List<ManufacturerDto> getByName(String name) {
        List<Manufacturer> manufacturerList = manufacturerRepository.getAllByNameContains(name);
        return manufacturerList.stream()
                .map(this::provideDto)
                .collect(Collectors.toList());
    }

    public ManufacturerDto getOptionalByName(String name){
        Manufacturer manufacturerOptional = manufacturerRepository.getOptionalByName(name).orElse(new Manufacturer());
        return provideDto(manufacturerOptional);
    }

    //opcja z mapperem
    //TODO
    public List<ManufacturerDto> getWithMapper(String name) {
        return manufacturerRepository.findAll().stream()
                .map(MAPPER::manufacturerToManufacturerDto)
                .collect(Collectors.toList());
    }

    //opcja z builderem
    //TODO
    private ManufacturerDto provideDto(Manufacturer manufacturer) {
        return ManufacturerDto.builder()
                .id(manufacturer.getId())
                .name(manufacturer.getName())
                .setOfCopierArticles(manufacturer.getSetOfCopierArticles())
                .setOfCopierModels(manufacturer.getSetOfCopierModels())
                .build();
    }

    public Manufacturer provideEntity(ManufacturerDto manufacturerDto) {
        return Manufacturer.builder()
                .id(manufacturerDto.getId())
                .name(manufacturerDto.getName())
                .setOfCopierArticles(manufacturerDto.getSetOfCopierArticles())
                .setOfCopierModels(manufacturerDto.getSetOfCopierModels())
                .build();
    }
}
