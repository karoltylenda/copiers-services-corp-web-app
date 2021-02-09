package com.tytanisukcesu.copiers.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.tytanisukcesu.copiers.entity.*;
import lombok.*;

import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class DeviceDto {

    private Long id;

    private ModelDto model;

    private String serialNumber;

    private CustomerDto customer;

    @JsonIgnore
    @EqualsAndHashCode.Exclude
    private Set<CounterDto> counters;

    private AddressDto address;

    private ContractDto contract;
}
