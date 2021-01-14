package com.tytanisukcesu.demo.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "articles")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String catalogueNumber;

    @Column(nullable = false)
    private boolean isConsumable; //czy eksploatacyjny

    @Column(nullable = false)
    private boolean isAlternative; //czy zamiennik

    @Column(nullable = false)
    private BigDecimal purchasePrice;

    @Column(nullable = false)
    private BigDecimal salePrice;

    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinColumn(name = "manufacturer_id")
    private Manufacturer manufacturer;

    @ElementCollection
    @CollectionTable(name = "models_for_articles")
    private Set<String> models;

    @Column(nullable = false)
    private Integer yield; //wydajnosc materialu w 1000 stron


}
