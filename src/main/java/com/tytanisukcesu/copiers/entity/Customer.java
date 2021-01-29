package com.tytanisukcesu.copiers.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "customers")
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String companyName;

    @Column(nullable = false,unique = true)
    private String taxId;

    private String regon;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "id")
    private Address address;

    private Long telephoneNumber;

    private String email;

    private String companySiteUrl;

    @OneToMany(
            mappedBy = "customer",
            cascade = CascadeType.ALL
    )
    private Set<Device> devices;

}
