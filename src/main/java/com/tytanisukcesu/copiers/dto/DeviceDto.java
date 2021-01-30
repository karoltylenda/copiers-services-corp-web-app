package com.tytanisukcesu.copiers.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tytanisukcesu.copiers.entity.*;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
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

    private ModelDto model;

    private String serialNumber;

    private CustomerDto customer;

    private Set<CounterDto> counters;

    private AddressDto address;

    private ContractDto contract;

}
