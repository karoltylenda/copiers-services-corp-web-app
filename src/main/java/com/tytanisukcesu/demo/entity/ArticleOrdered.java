package com.tytanisukcesu.demo.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "articles_ordered")
public class ArticleOrdered {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal price;

    @ManyToOne
    private Article article;
}
