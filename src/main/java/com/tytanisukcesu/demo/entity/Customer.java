package com.tytanisukcesu.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "customers")
@Builder
@Getter
@Setter
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String companyName;

    @Column(nullable = false,unique = true)
    private String nip;

    private String regon;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(referencedColumnName = "id")
    private Address address;

    private Long telephoneNumber;

    private String email;

    private String companySiteUrl;

    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    private Set<Device> devices;

    public Customer() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(nip, customer.nip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nip);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", companyName='" + companyName + '\'' +
                ", nip='" + nip + '\'' +
                ", regon='" + regon + '\'' +
                ", address=" + address +
                ", telephoneNumber=" + telephoneNumber +
                ", email='" + email + '\'' +
                ", companySiteUrl='" + companySiteUrl + '\'' +
                '}';
    }
}
