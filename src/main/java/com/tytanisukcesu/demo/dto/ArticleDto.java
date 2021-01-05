package com.tytanisukcesu.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tytanisukcesu.demo.entity.Manufacturer;
import com.tytanisukcesu.demo.entity.Model;
import lombok.*;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ArticleDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private String name;

    private String catalogueNumber;

    private boolean isConsumable;

    private boolean isAlternative;

    private BigDecimal purchasePrice;

    private BigDecimal salePrice;

    private Manufacturer manufacturer;

    private Integer yield;

    private Set<Model> models;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArticleDto)) return false;
        ArticleDto that = (ArticleDto) o;
        return Objects.equals(catalogueNumber, that.catalogueNumber) && Objects.equals(manufacturer, that.manufacturer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(catalogueNumber, manufacturer);
    }
}
