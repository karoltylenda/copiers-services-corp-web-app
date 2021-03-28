package com.tytanisukcesu.copiers.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
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

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @JsonIgnoreProperties({
            "consumables"
    })
    private ModelDto model;

    private String serialNumber;

    @EqualsAndHashCode.Exclude
    @JsonIgnoreProperties({
            "devices",
            "users"
    })
    private CustomerDto customer;

    private Set<CounterDto> counters;

    @JsonIgnoreProperties({
            "device",
            "customer",
            "addressType"
    })
    private AddressDto address;

    @EqualsAndHashCode.Exclude
    @JsonIgnoreProperties({
            "device",
            "copierSettlementSet"
    })
    private ContractDto contract;
}
