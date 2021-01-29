package com.tytanisukcesu.copiers.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tytanisukcesu.copiers.entity.Article;
import com.tytanisukcesu.copiers.entity.Manufacturer;
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

    private Manufacturer manufacturer;

    private PrintingFormat printingFormat;

    @JsonIgnore
    private Set<Article> consumables;

}
