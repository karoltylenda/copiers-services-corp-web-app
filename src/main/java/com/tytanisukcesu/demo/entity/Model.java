package com.tytanisukcesu.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "models")
public class Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private boolean printsInColor;
    private Integer productionYear;
    @Column(nullable = false)
    private Integer printingSpeed;
    @ManyToMany(mappedBy = "models")
    @JsonIgnore
    private Set<Article> consumables;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    @JoinColumn(name = "manufacturer_id")
    private Manufacturer manufacturer;

    @Column
    private PrintingFormat printingFormat;

    @OneToMany(mappedBy = "model")
    @JsonIgnore
    private Set<Device> devices;

    public Model() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean getPrintsInColor() {
        return printsInColor;
    }

    public void setPrintsInColor(boolean printsInColor) {
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

    public Set<Article> getConsumables() {
        return consumables;
    }

    public void setConsumables(Set<Article> consumables) {
        this.consumables = consumables;
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
                ", consumables=" + consumables +
                ", manufacturer=" + manufacturer +
                ", printingFormat=" + printingFormat +
                '}';
    }
}
