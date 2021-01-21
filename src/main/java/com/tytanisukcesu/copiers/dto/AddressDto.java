package com.tytanisukcesu.copiers.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    private CustomerDto customer;

}
