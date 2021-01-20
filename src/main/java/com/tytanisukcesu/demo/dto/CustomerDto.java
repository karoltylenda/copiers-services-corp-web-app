package com.tytanisukcesu.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tytanisukcesu.demo.entity.Address;
import com.tytanisukcesu.demo.entity.Contract;
import com.tytanisukcesu.demo.entity.Device;
import lombok.*;

import javax.persistence.*;
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
