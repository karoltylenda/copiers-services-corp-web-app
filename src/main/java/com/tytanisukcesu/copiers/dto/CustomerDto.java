package com.tytanisukcesu.copiers.dto;

import com.fasterxml.jackson.annotation.*;
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

    private Long id;

    private String companyName;

    private String taxId;

    private String regon;

    private AddressDto address;

    private Long telephoneNumber;

    private String email;

    private String companySiteUrl;

    @JsonManagedReference
    private Set<DeviceDto> devices;

}
