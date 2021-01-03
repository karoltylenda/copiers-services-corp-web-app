package com.tytanisukcesu.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "articles")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(unique = true, nullable = false)
    private String catalogueNumber;
    @Column(nullable = false)
    private boolean isConsumable; //czy eksploatacyjny
    @Column(nullable = false)
    private boolean isAlternative; //czy zamiennik
    @Column(nullable = false)
    private BigDecimal purchasePrice;
    @Column(nullable = false)
    private BigDecimal salePrice;

    //TODO - dlaczego na all nie dziala
    @ManyToOne(cascade = CascadeType.MERGE)
    @JsonIgnore
    @JoinColumn(name = "manufacturer_id")
    private Manufacturer manufacturer;

    @Column(nullable = false)
    private Integer yield; //wydajnosc materialu w 1000 stron
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "articles_and_models_connection",
            joinColumns = @JoinColumn(name = "article_id"),
            inverseJoinColumns = @JoinColumn(name = "model_id")
    )
    private Set<Model> models;

    public Article() {
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

    public boolean getConsumable() {
        return isConsumable;
    }

    public void setConsumable(boolean consumable) {
        isConsumable = consumable;
    }

    public boolean getAlternative() {
        return isAlternative;
    }

    public void setAlternative(boolean alternative) {
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

    public Set<Model> getModels() {
        return models;
    }

    public void setModels(Set<Model> models) {
        this.models = models;
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
                ", models=" + models +
                '}';
    }
}
