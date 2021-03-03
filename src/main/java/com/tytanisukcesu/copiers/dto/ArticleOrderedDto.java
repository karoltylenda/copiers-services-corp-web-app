package com.tytanisukcesu.copiers.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class ArticleOrderedDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private Long quantity;

    private BigDecimal price;

    private BigDecimal totalPrice;

    @JsonIgnoreProperties({
            "name",
            "models",
            "catalogueNumber",
            "isConsumable",
            "manufacturer",
    })
    private ArticleDto article;

    @JsonIgnore
    private ServiceOrderDto serviceOrder;

}
