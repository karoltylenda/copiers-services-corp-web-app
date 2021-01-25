package com.tytanisukcesu.copiers.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tytanisukcesu.copiers.entity.Address;
import com.tytanisukcesu.copiers.entity.Device;
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

    private String taxId;

    private String regon;

    private Address address;

    private Long telephoneNumber;

    private String email;

    private String companySiteUrl;

    private Set<Device> devices;

}
