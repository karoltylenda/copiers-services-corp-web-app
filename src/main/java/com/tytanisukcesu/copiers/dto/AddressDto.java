package com.tytanisukcesu.copiers.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tytanisukcesu.copiers.types.AddressType;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class AddressDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private String province;

    private String city;

    private String postCode;

    private String street;

    private String houseNumber;

    private String apartmentNumber;

    @EqualsAndHashCode.Exclude
    @JsonIgnoreProperties({
            "address",
            "devices",
            "users"
    })
    private CustomerDto customer;

    @EqualsAndHashCode.Exclude
    @JsonIgnoreProperties({
            "customer",
            "counters",
            "address",
            "contract",
    })
    private DeviceDto device;

    private AddressType addressType;

}
