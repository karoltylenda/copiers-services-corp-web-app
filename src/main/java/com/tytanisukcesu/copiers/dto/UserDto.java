package com.tytanisukcesu.copiers.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeId;
import com.tytanisukcesu.copiers.entity.Customer;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class UserDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private String username;

    private String firstName;

    private String lastName;

    @JsonIgnore
    private String password;

    private String role;

    @JsonIgnoreProperties({
            "companyName",
            "taxId",
            "regon",
            "address",
            "telephoneNumber",
            "email",
            "companySiteUrl",
            "devices",
            "users"
    })
    private CustomerDto customer;
}
