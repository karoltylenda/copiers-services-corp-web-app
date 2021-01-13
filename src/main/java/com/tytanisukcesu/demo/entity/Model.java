package com.tytanisukcesu.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "models")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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

    //todo check if persist is redundant
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "manufacturer_id")
    private Manufacturer manufacturer;

    @Column(unique = true)
    private String serialNumber;

    private PrintingFormat printingFormat;
    private BigDecimal monoPagePrice;
    private BigDecimal colorPagePrice;

    @OneToMany(mappedBy = "device", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Counter> counters;

}
