package com.tytanisukcesu.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "manufacturers")
@Builder
@AllArgsConstructor
public class Manufacturer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @OneToMany(mappedBy = "manufacturer")
    @JsonIgnore
    private Set<Model> setOfCopierModels;

    @OneToMany(mappedBy = "manufacturer")
    @JsonIgnore
    private Set<Article> setOfCopierArticles;


    public Manufacturer() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Model> getSetOfCopierModels() {
        return setOfCopierModels;
    }

    public void setSetOfCopierModels(Set<Model> setOfCopierModels) {
        this.setOfCopierModels = setOfCopierModels;
    }

    public Set<Article> getSetOfCopierArticles() {
        return setOfCopierArticles;
    }

    public void setSetOfCopierArticles(Set<Article> setOfCopierArticles) {
        this.setOfCopierArticles = setOfCopierArticles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Manufacturer that = (Manufacturer) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Manufacturer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", setOfCopierModels=" + setOfCopierModels +
                ", setOfCopierArticles=" + setOfCopierArticles +
                '}';
    }
}
