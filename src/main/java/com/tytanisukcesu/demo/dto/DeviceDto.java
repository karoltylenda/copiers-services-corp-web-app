package com.tytanisukcesu.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class DeviceDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private ModelDto model;

    private String serialNumber;

    private CustomerDto customer;

    private BigDecimal monoPagePrice;

    private BigDecimal colorPagePrice;

    private Set<CounterDto> counters;

}
