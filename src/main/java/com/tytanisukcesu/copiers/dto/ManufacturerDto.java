package com.tytanisukcesu.copiers.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tytanisukcesu.copiers.entity.Article;
import com.tytanisukcesu.copiers.entity.Model;
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

    @JsonIgnore
    private Set<Model> models;

    @JsonIgnore
    private Set<Article> articles;

}