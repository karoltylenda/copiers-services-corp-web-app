package com.tytanisukcesu.copiers.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    private String nip;

    private String regon;

    private AddressDto address;

    private Long telephoneNumber;

    private String email;

    private String companySiteUrl;

    private ContractDto contract;

    private Set<DeviceDto> devices;

}
