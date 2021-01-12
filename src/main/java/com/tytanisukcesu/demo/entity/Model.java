package com.tytanisukcesu.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "models")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
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
    @ManyToMany(mappedBy = "models",cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Article> consumables;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "manufacturer_id")
    private Manufacturer manufacturer;

    @Column
    private PrintingFormat printingFormat;

    @OneToMany(mappedBy = "model",cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Device> devices;

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
                ", devices=" + devices +
                '}';
    }
}
