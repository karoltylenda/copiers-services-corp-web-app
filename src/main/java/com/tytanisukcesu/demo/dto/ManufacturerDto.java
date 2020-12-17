package com.tytanisukcesu.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tytanisukcesu.demo.entity.Article;
import com.tytanisukcesu.demo.entity.Model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;
import java.util.UUID;

public class ManufacturerDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID id;

    private String name;

    @JsonIgnore
    private Set<Model> setOfCopierModels;

    @JsonIgnore
    private Set<Article> setOfCopierArticles;

    public ManufacturerDto() {
    }

    @Override
    public String toString() {
        return "ManufacturerDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", setOfCopierModels=" + setOfCopierModels +
                ", setOfCopierArticles=" + setOfCopierArticles +
                '}';
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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



}