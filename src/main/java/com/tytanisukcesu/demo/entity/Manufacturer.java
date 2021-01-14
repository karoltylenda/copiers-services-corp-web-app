package com.tytanisukcesu.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonMerge;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "manufacturers")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Manufacturer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @OneToMany(mappedBy = "manufacturer")
//    fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
//    @EqualsAndHashCode.Exclude
//    @ToString.Exclude
    @JsonIgnore
    @JsonMerge
    private Set<Model> setOfCopierModels;

    @OneToMany(mappedBy = "manufacturer")
    @JsonIgnore
    private Set<Article> articles;


}
