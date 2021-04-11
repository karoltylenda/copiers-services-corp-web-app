package com.tytanisukcesu.copiers.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "devices")
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(
            fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(referencedColumnName = "id")
    private Model model;

    @Column(unique = true)
    private String serialNumber;

    @ManyToOne(
            fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(referencedColumnName = "id")
    private Customer customer;

    @OneToMany(
            mappedBy = "device",
            fetch = FetchType.EAGER
    )
    @EqualsAndHashCode.Exclude
    //todo - sortowanie po dacie malejąco przy wyswietlaniu w json
    private Set<Counter> counters;

    @OneToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    @JoinColumn(referencedColumnName = "id")
    private Address address;

    @OneToOne(mappedBy = "device")
    private Contract contract;

}
