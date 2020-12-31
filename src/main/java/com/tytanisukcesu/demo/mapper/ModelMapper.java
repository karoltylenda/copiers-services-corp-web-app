package com.tytanisukcesu.demo.mapper;

import com.tytanisukcesu.demo.dto.ModelDto;
import com.tytanisukcesu.demo.entity.Model;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ModelMapper {

    ModelMapper MAPPER = Mappers.getMapper(ModelMapper.class);

    @Mappings({
            @Mapping(target = "id", source = "model.id"),
            @Mapping(target = "name", source = "model.name"),
            @Mapping(target = "printsInColor", source = "model.printsInColor"),
            @Mapping(target = "productionYear", source = "model.productionYear"),
            @Mapping(target = "printingSpeed", source = "model.printingSpeed"),
            @Mapping(target = "consumables", source = "model.consumables"),
            @Mapping(target = "manufacturer", source = "model.manufacturer"),
            @Mapping(target = "printingFormat", source = "model.printingFormat")
    })
    ModelDto modelToModelDto(final Model model);

    @Mappings({
            @Mapping(target = "id", source = "modelDto.id"),
            @Mapping(target = "name", source = "modelDto.name"),
            @Mapping(target = "printsInColor", source = "modelDto.printsInColor"),
            @Mapping(target = "productionYear", source = "modelDto.productionYear"),
            @Mapping(target = "printingSpeed", source = "modelDto.printingSpeed"),
            @Mapping(target = "consumables", source = "modelDto.consumables"),
            @Mapping(target = "manufacturer", source = "modelDto.manufacturer"),
            @Mapping(target = "printingFormat", source = "modelDto.printingFormat")
    })
    Model modelDtoToModel(final ModelDto modelDto);
}
