package com.tytanisukcesu.copiers.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @JsonIgnoreProperties({
            "models",
            "articles"
    })
    private ManufacturerDto manufacturer;

    @JsonIgnoreProperties({
            "manufacturer",
            "consumables"
    })
    @EqualsAndHashCode.Exclude
    private Set<ModelDto> models;

}
