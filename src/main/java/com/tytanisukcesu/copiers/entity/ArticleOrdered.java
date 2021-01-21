package com.tytanisukcesu.copiers.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "articles_ordered")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleOrdered {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantity;

    private BigDecimal price;

    private BigDecimal totalPrice;

    @ManyToOne(
            fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE}
    )
    @JoinColumn(referencedColumnName = "id")
    private Article article;

    @ManyToOne(
            fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE}
    )
    @JoinColumn(referencedColumnName = "id")
    private ServiceOrder serviceOrder;

}
