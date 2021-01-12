package com.tytanisukcesu.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tytanisukcesu.demo.entity.Article;
import com.tytanisukcesu.demo.entity.Model;
import lombok.*;

import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ManufacturerDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private String name;

    private Set<Model> setOfCopierModels;

    private Set<Article> setOfCopierArticles;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ManufacturerDto that = (ManufacturerDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(setOfCopierModels, that.setOfCopierModels) &&
                Objects.equals(setOfCopierArticles, that.setOfCopierArticles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, setOfCopierModels, setOfCopierArticles);
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
}
