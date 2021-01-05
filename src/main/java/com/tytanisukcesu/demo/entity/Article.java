package com.tytanisukcesu.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "articles")
@Getter
@Setter
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

    //TODO - dlaczego na all nie dziala
    @ManyToOne(cascade = CascadeType.MERGE)
    @JsonIgnore
    @JoinColumn(name = "manufacturer_id")
    private Manufacturer manufacturer;

    @Column(nullable = false)
    private Integer yield; //wydajnosc materialu w 1000 stron
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "articles_and_models_connection",
            joinColumns = @JoinColumn(name = "article_id"),
            inverseJoinColumns = @JoinColumn(name = "model_id")
    )
    private Set<Model> models;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return Objects.equals(catalogueNumber, article.catalogueNumber) &&
                Objects.equals(manufacturer, article.manufacturer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(catalogueNumber, manufacturer);
    }

}
