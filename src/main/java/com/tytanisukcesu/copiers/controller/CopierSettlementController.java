package com.tytanisukcesu.copiers.controller;

import com.tytanisukcesu.copiers.dto.CopiersSettlementDto;
import com.tytanisukcesu.copiers.entity.CopierSettlement;
import com.tytanisukcesu.copiers.service.CopierSettlementService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/copiersettlement")
@RequiredArgsConstructor
public class CopierSettlementController {

    private final CopierSettlementService copierSettlementService;
    private final ModelMapper modelMapper;

    @GetMapping
    public List<CopiersSettlementDto> getAll(){
        List<CopierSettlement> copierSettlementList = copierSettlementService.findAll();
        return copierSettlementList.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}")
    public CopiersSettlementDto getById(@PathVariable("id") Long id){
        CopierSettlement copierSettlement = copierSettlementService.findById(id);
        return convertToDto(copierSettlement);
    }

    @PostMapping
    public CopiersSettlementDto save(@RequestBody CopiersSettlementDto copiersSettlementDto){
        CopierSettlement copierSettlement = convertToEntity(copiersSettlementDto);
        CopierSettlement copierSettlementSaved = copierSettlementService.save(copierSettlement);
        return convertToDto(copierSettlementSaved);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id){
        if(copierSettlementService.delete(id)){
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    private CopierSettlement convertToEntity(CopiersSettlementDto copiersSettlementDto){
        CopierSettlement copierSettlement = modelMapper.map(copiersSettlementDto, CopierSettlement.class);
        return copierSettlement;
    }

    private CopiersSettlementDto convertToDto(CopierSettlement copierSettlement){
        CopiersSettlementDto copiersSettlementDto = modelMapper.map(copierSettlement, CopiersSettlementDto.class);
        return copiersSettlementDto;
    }
}
