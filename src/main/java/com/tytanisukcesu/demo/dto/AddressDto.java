package com.tytanisukcesu.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tytanisukcesu.demo.entity.Customer;
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

    private Customer customer;

    private boolean isInvoice;


}
