package com.tytanisukcesu.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tytanisukcesu.demo.entity.Article;
import com.tytanisukcesu.demo.entity.Manufacturer;
import com.tytanisukcesu.demo.entity.PrintingFormat;
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

    private Set<Article> consumables;

    private Manufacturer manufacturer;

    private PrintingFormat printingFormat;

    @JsonIgnore
    private Set<Device> devices;

}
