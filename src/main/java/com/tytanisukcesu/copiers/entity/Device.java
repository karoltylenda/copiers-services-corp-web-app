package com.tytanisukcesu.copiers.entity;

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

    @OneToMany(mappedBy = "device")
    @EqualsAndHashCode.Exclude
    private Set<Counter> counters;

    @OneToOne(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    @JoinColumn(referencedColumnName = "id")
    private Address address;

    @OneToOne(
            fetch = FetchType.EAGER,
            cascade = CascadeType.MERGE)
    @JoinColumn(referencedColumnName = "id")
    private Contract contract;

}
