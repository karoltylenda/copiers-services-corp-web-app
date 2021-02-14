package com.tytanisukcesu.copiers.dto;

import com.fasterxml.jackson.annotation.*;
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
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public class DeviceDto {

    @JsonProperty("id")
    private Long id;

    private ModelDto model;

    @JsonView(ContractDto.class)
    private String serialNumber;

    @EqualsAndHashCode.Exclude
    @JsonBackReference
    private CustomerDto customer;

    private Set<CounterDto> counters;

    private AddressDto address;

    private ContractDto contract;
}
