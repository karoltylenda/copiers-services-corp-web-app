package com.tytanisukcesu.demo.entity;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
public class Model {

    @Id
    private UUID id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Boolean printsInColor;
    private Integer productionYear;
    @Column(nullable = false)
    private Integer printingSpeed;
    @ManyToMany(mappedBy = "setOfModels")
    private Set<Article> setOfConsumables;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "model_Id", referencedColumnName = "id")
    private Manufacturer manufacturer;
    @Column(nullable = false)
    private PrintingFormat printingFormat;

    public Model() {

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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


    public PrintingFormat getPrintingFormat() {
        return printingFormat;
    }

    public void setPrintingFormat(PrintingFormat printingFormat) {
        this.printingFormat = printingFormat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        Model model = (Model) o;
        return Objects.equals(name, model.name) &&
                Objects.equals(manufacturer, model.manufacturer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, manufacturer);
    }

    @Override
    public String toString() {
        return "Model{" +
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
