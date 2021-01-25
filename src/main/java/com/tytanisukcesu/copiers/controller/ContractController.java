package com.tytanisukcesu.copiers.controller;

import com.tytanisukcesu.copiers.dto.ContractDto;
import com.tytanisukcesu.copiers.entity.Contract;
import com.tytanisukcesu.copiers.service.ContractService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/contracts")
@RequiredArgsConstructor
public class ContractController {

    private final ContractService contractService;
    private final ModelMapper modelMapper;

    @GetMapping
    public List<ContractDto> getAll(){
        List<Contract> contracts = contractService.findAll();
        return contracts.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}")
    public ContractDto getById(@PathVariable("id") Long id){
        Contract contract = contractService.findById(id);
        return convertToDto(contract);
    }

    @PostMapping
    public ContractDto save(@RequestBody ContractDto contractDto){
        Contract contract = convertToEntity(contractDto);
        Contract contractSaved = contractService.save(contract);
        return convertToDto(contractSaved);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id){
        if(contractService.delete(id)){
            return ResponseEntity.accepted().build();
        }else{
            return ResponseEntity.status(404).build();
        }
    }

    @PutMapping(value = "/{id}")
    public ContractDto update(@PathVariable("id") Long id, @RequestBody ContractDto contractDto){
        Contract contract = convertToEntity(contractDto);
        Contract contractSaved = contractService.update(id,contract);
        return convertToDto(contractSaved);
    }

    private ContractDto convertToDto(Contract contract) {
        ContractDto contractDto = modelMapper.map(contract, ContractDto.class);
        return contractDto;
    }

    private Contract convertToEntity(ContractDto contractDto){
        Contract contract = modelMapper.map(contractDto, Contract.class);
        return contract;
    }
}
