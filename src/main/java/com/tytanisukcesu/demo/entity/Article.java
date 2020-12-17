package com.tytanisukcesu.demo.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
public class Article {

    @Id
    private UUID id;
    @Column(nullable = false)
    private String name;
    @Column(unique = true, nullable = false)
    private String catalogueNumber;
    @Column(nullable = false)
    private Boolean isConsumable;
    @Column(nullable = false)
    private Boolean isAlternative;
    @Column(nullable = false)
    private BigDecimal purchasePrice;
    @Column(nullable = false)
    private BigDecimal salePrice;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "manufacurer_ID")
    private Manufacturer manufacturer;
    @Column(nullable = false)
    private Integer yield;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "model_article",
            joinColumns = @JoinColumn(name = "articleId", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "modelId", referencedColumnName = "id"))
    private Set<Model> setOfModels;

    public Article() {
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

    public Integer getYield() {
        return yield;
    }

    public void setYield(Integer yield) {
        this.yield = yield;
    }

    public Set<Model> getSetOfModels() {
        return setOfModels;
    }

    public void setSetOfModels(Set<Model> setOfModels) {
        this.setOfModels = setOfModels;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return Objects.equals(catalogueNumber, article.catalogueNumber) &&
                Objects.equals(manufacturer, article.manufacturer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(catalogueNumber, manufacturer);
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", catalogueNumber='" + catalogueNumber + '\'' +
                ", isConsumable=" + isConsumable +
                ", isAlternative=" + isAlternative +
                ", purchasePrice=" + purchasePrice +
                ", salePrice=" + salePrice +
                ", manufacturer=" + manufacturer +
                ", yield=" + yield +
                ", setOfModels=" + setOfModels +
                '}';
    }
}
