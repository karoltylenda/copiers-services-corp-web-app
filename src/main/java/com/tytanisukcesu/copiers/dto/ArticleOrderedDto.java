package com.tytanisukcesu.copiers.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tytanisukcesu.copiers.entity.Article;
import com.tytanisukcesu.copiers.entity.ServiceOrder;
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

    private Long id;

    private Long quantity;

    private BigDecimal price;

    private BigDecimal totalPrice;

    private ArticleDto article;

    @JsonIgnore
    private ServiceOrderDto serviceOrder;

}
