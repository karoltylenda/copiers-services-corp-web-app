package com.tytanisukcesu.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.persistence.*;

@Entity
@Table(name = "devices")
@Builder
@AllArgsConstructor
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "modelId", referencedColumnName = "id")
    private Model model;

    @Column(unique = true)
    private String serialNumber;

    @ManyToOne
    @JoinColumn(name = "customerId", referencedColumnName = "id")
    private Customer customer;

    private String postCode;
    private String street;
    private String city;
    private String houseNumber;
    private String apartmentNumber;

    //licznik wydrukowanych stron


    public Device() {
    }
}
