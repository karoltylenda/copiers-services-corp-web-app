package com.tytanisukcesu.copiers.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tytanisukcesu.copiers.types.AddresType;
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

    @JsonIgnore
    private CustomerDto customer;

    @JsonIgnore
    private DeviceDto device;

    @JsonIgnore
    private AddresType addresType;

}
