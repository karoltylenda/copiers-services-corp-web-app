package com.tytanisukcesu.copiers.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tytanisukcesu.copiers.types.PrintingFormat;
import lombok.*;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class ModelDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private String name;

    private boolean printsInColor;

    private Integer productionYear;

    private Integer printingSpeed;

    @JsonIgnoreProperties({
            "models",
            "articles"
    })
    private ManufacturerDto manufacturer;

    private PrintingFormat printingFormat;

    @EqualsAndHashCode.Exclude
    @JsonIgnoreProperties({
            "models",
            "manufacturer"
    })
    private Set<ArticleDto> consumables;

}
