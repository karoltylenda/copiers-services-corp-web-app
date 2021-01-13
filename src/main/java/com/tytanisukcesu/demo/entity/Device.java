package com.tytanisukcesu.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonMerge;
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
@EqualsAndHashCode
@ToString
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "modelId", referencedColumnName = "id")
    @JsonIgnore
    @JsonMerge
    private Model model;

    @Column(unique = true)
    private String serialNumber;

    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "customerId", referencedColumnName = "id")
    @JsonIgnore
    @JsonMerge
    private Customer customer;

    private BigDecimal monoPagePrice;

    private BigDecimal colorPagePrice;

    @OneToMany(mappedBy = "device")
    @JsonIgnore
    @JsonMerge
    private Set<Counter> counters;

}
