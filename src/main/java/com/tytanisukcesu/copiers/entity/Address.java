package com.tytanisukcesu.copiers.entity;

import com.tytanisukcesu.copiers.types.AddresType;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "addresses")
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String province;

    private String city;

    @Column(nullable = false)
    private String postCode;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private String houseNumber;

    private String apartmentNumber;

    @OneToOne(mappedBy = "address")
    private Customer customer;

    @OneToOne(mappedBy = "address")
    private Device device;

    private AddresType addresType;

}
