package com.tytanisukcesu.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tytanisukcesu.demo.entity.Address;
import lombok.*;

import java.util.Objects;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class CustomerDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private String companyName;

    private String nip;

    private String regon;

    private Address address;

    private Long telephoneNumber;

    private String email;

    private String companySiteUrl;

    public CustomerDto() {
    }

    @Override
    public String toString() {
        return "CustomerDto{" +
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerDto that = (CustomerDto) o;
        return Objects.equals(nip, that.nip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nip);
    }
}
