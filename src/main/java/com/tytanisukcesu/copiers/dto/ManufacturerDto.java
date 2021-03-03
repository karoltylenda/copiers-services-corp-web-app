package com.tytanisukcesu.copiers.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
public class ManufacturerDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private String name;

    @EqualsAndHashCode.Exclude
    @JsonIgnoreProperties({
            "name",
            "printsInColor",
            "productionYear",
            "printingSpeed",
            "manufacturer",
            "printingFormat",
            "consumables"
    })
    private Set<ModelDto> models;

    @EqualsAndHashCode.Exclude
    @JsonIgnoreProperties({
            "name",
            "catalogueNumber",
            "consumable",
            "manufacturer",
            "models"
    })
    private Set<ArticleDto> articles;

}
