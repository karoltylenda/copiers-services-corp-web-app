package com.tytanisukcesu.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "customers")
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String companyName;

    @Column(nullable = false)
    private String nip;

    private String regon;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "addressId")
    private Address address;

    private Long telephoneNumber;

    private String email;

    private String companySiteUrl;

    public Customer() {
    }
}
