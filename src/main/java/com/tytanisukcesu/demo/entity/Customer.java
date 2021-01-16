package com.tytanisukcesu.demo.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "customers")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String companyName;

    @Column(nullable = false,unique = true)
    private String nip;

    private String regon;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private Set<Address> addresses;

    private Long telephoneNumber;

    private String email;

    private String companySiteUrl;

    @OneToMany(mappedBy = "customer")
    private Set<Model> models;


}
