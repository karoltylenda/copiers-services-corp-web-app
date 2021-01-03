package com.tytanisukcesu.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tytanisukcesu.demo.entity.Customer;
import lombok.Builder;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.util.Set;

@Data
@Builder
public class AddressDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private String province;

    private String city;

    private String postCode;

    private String street;

    private String houseNumber;

    private String apartmentNumber;

    private Set<Customer> customers;

    public AddressDto() {
    }
}
