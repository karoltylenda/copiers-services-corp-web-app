package com.tytanisukcesu.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "devices")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "modelId", referencedColumnName = "id")
    private Model model;

    @Column(unique = true)
    private String serialNumber;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "customerId", referencedColumnName = "id")
    private Customer customer;

    private BigDecimal monoPagePrice;

    private BigDecimal colorPagePrice;

    @JsonIgnore
    @OneToMany(mappedBy = "device")
    private Set<Counter> counters;

}
