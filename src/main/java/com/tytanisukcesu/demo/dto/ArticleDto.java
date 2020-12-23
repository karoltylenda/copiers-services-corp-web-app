package com.tytanisukcesu.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tytanisukcesu.demo.entity.Manufacturer;
import com.tytanisukcesu.demo.entity.Model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

public class ArticleDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private String name;

    private String catalogueNumber;

    private Boolean isConsumable;

    private Boolean isAlternative;

    private BigDecimal purchasePrice;

    private BigDecimal salePrice;

    private Manufacturer manufacturer;

    private Integer yield;

    private Set<Model> models;

    public ArticleDto() {
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

    public String getCatalogueNumber() {
        return catalogueNumber;
    }

    public void setCatalogueNumber(String catalogueNumber) {
        this.catalogueNumber = catalogueNumber;
    }

    public Boolean getConsumable() {
        return isConsumable;
    }

    public void setConsumable(Boolean consumable) {
        isConsumable = consumable;
    }

    public Boolean getAlternative() {
        return isAlternative;
    }

    public void setAlternative(Boolean alternative) {
        isAlternative = alternative;
    }

    public BigDecimal getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(BigDecimal purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Integer getYield() {
        return yield;
    }

    public void setYield(Integer yield) {
        this.yield = yield;
    }

    public Set<Model> getModels() {
        return models;
    }

    public void setModels(Set<Model> models) {
        this.models = models;
    }

    @Override
    public String toString() {
        return "ArticleDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", catalogueNumber='" + catalogueNumber + '\'' +
                ", isConsumable=" + isConsumable +
                ", isAlternative=" + isAlternative +
                ", purchasePrice=" + purchasePrice +
                ", salePrice=" + salePrice +
                ", manufacturer=" + manufacturer +
                ", yield=" + yield +
                ", models=" + models +
                '}';
    }
}
