package com.tytanisukcesu.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonMerge;
import lombok.*;
import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "manufacturers")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Manufacturer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @OneToMany(mappedBy = "manufacturer")
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @JsonMerge
    private Set<Model> models;

    @OneToMany(mappedBy = "manufacturer")
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @JsonMerge
    private Set<Article> articles;

}
