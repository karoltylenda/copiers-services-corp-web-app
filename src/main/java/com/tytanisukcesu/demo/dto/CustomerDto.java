package com.tytanisukcesu.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tytanisukcesu.demo.entity.Address;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
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
}
