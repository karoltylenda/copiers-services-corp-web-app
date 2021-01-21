package com.tytanisukcesu.copiers.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class ArticleDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private String name;

    private String catalogueNumber;

    private boolean isConsumable;

    private ManufacturerDto manufacturer;

    private Set<ModelDto> models;

}
