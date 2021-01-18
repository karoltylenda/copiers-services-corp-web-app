package com.tytanisukcesu.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "manufacturers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Manufacturer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @JsonIgnore
    @OneToMany(
            mappedBy = "manufacturer",
            cascade = CascadeType.REMOVE)
    @EqualsAndHashCode.Exclude
    private Set<Model> models;

    @JsonIgnore
    @OneToMany(
            mappedBy = "manufacturer",
            cascade = CascadeType.REMOVE)
    @EqualsAndHashCode.Exclude
    private Set<Article> articles;

}
