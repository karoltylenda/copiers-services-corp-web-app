package com.tytanisukcesu.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tytanisukcesu.demo.entity.Article;
import com.tytanisukcesu.demo.entity.Manufacturer;
import com.tytanisukcesu.demo.entity.PrintingFormat;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

public class ModelDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID id;

    private String name;

    private Boolean printsInColor;

    private Integer productionYear;

    private Integer printingSpeed;

    @JsonIgnore
    private Set<Article> setOfConsumables;

    private Manufacturer manufacturer;

    private PrintingFormat printingFormat;

    public ModelDto() {
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

    public Boolean getPrintsInColor() {
        return printsInColor;
    }

    public void setPrintsInColor(Boolean printsInColor) {
        this.printsInColor = printsInColor;
    }

    public Integer getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(Integer productionYear) {
        this.productionYear = productionYear;
    }

    public Integer getPrintingSpeed() {
        return printingSpeed;
    }

    public void setPrintingSpeed(Integer printingSpeed) {
        this.printingSpeed = printingSpeed;
    }

    public Set<Article> getSetOfConsumables() {
        return setOfConsumables;
    }

    public void setSetOfConsumables(Set<Article> setOfConsumables) {
        this.setOfConsumables = setOfConsumables;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public PrintingFormat getPrintingFormat() {
        return printingFormat;
    }

    public void setPrintingFormat(PrintingFormat printingFormat) {
        this.printingFormat = printingFormat;
    }

    @Override
    public String toString() {
        return "ModelDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", printsInColor=" + printsInColor +
                ", productionYear=" + productionYear +
                ", printingSpeed=" + printingSpeed +
                ", setOfConsumables=" + setOfConsumables +
                ", manufacturer=" + manufacturer +
                ", printingFormat=" + printingFormat +
                '}';
    }
}