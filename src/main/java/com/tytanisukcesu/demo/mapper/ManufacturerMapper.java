package com.tytanisukcesu.demo.mapper;

import com.tytanisukcesu.demo.dto.ManufacturerDto;
import com.tytanisukcesu.demo.entity.Manufacturer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ManufacturerMapper {

    ManufacturerMapper MAPPER = Mappers.getMapper(ManufacturerMapper.class);

    @Mappings({
            @Mapping(target = "id", source = "manufacturer.id"),
            @Mapping(target = "name", source = "manufacturer.name"),
            @Mapping(target = "setOfCopierModels", source = "manufacturer.setOfCopierModels"),
            @Mapping(target = "setOfCopierArticles", source = "manufacturer.setOfCopierArticles")
    })
    ManufacturerDto manufacturerToManufacturerDto(final Manufacturer manufacturer);

    @Mappings({
            @Mapping(target = "id", source = "manufacturerDto.id"),
            @Mapping(target = "name", source = "manufacturerDto.name"),
            @Mapping(target = "setOfCopierModels", source = "manufacturerDto.setOfCopierModels"),
            @Mapping(target = "setOfCopierArticles", source = "manufacturerDto.setOfCopierArticles")
    })
    Manufacturer manufacturerDtoToManufacturer(final ManufacturerDto manufacturerDto);

}
