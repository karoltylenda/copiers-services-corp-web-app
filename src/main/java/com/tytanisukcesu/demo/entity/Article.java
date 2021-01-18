package com.tytanisukcesu.demo.entity;

import lombok.*;
import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "articles")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(unique = true, nullable = false)
    private String catalogueNumber;
    @Column(nullable = false)
    private boolean isConsumable;

    @ManyToOne(
            fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "manufacturer_id")
    private Manufacturer manufacturer;

    @EqualsAndHashCode.Exclude
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "articles_and_models_connection",
            joinColumns = @JoinColumn(name = "article_id"),
            inverseJoinColumns = @JoinColumn(name = "model_id")
    )
    private Set<Model> models;

}
