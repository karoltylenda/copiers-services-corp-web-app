package com.tytanisukcesu.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tytanisukcesu.demo.entity.Article;
import com.tytanisukcesu.demo.entity.Model;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.Set;

@Builder
@AllArgsConstructor
public class ManufacturerDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private String name;

    private Set<Model> setOfCopierModels;

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



}
