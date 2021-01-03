package com.tytanisukcesu.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tytanisukcesu.demo.entity.Article;
import com.tytanisukcesu.demo.entity.Manufacturer;
import com.tytanisukcesu.demo.entity.PrintingFormat;
import java.util.Set;

public class ModelDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private String name;

    private boolean printsInColor;

    private Integer productionYear;

    private Integer printingSpeed;

    //FIXME - zmian na dto
    private Set<Article> consumables;

    private Manufacturer manufacturer;

    private PrintingFormat printingFormat;

    public ModelDto() {
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
                ", consumables=" + consumables +
                ", manufacturer=" + manufacturer +
                ", printingFormat=" + printingFormat +
                '}';
    }
}
