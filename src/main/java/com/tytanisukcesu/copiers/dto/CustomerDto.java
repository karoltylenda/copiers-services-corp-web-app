package com.tytanisukcesu.copiers.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tytanisukcesu.copiers.entity.User;
import lombok.*;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class CustomerDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private String companyName;

    private String taxId;

    private String regon;

    @JsonIgnoreProperties({
            "customer",
            "device",
            "addressType"
    })
    private AddressDto address;

    private String telephoneNumber;

    private String email;

    private String companySiteUrl;

    @EqualsAndHashCode.Exclude
    @JsonIgnoreProperties({
            "model",
            "counters",
            "customer",
            "contract",
            "address",
    })
    private Set<DeviceDto> devices;

    @EqualsAndHashCode.Exclude
    @JsonIgnoreProperties({
            "username",
            "password",
            "role",
            "customer"
    })
    private Set<UserDto> users;


}
