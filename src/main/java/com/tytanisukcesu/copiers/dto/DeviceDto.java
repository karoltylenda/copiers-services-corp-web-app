package com.tytanisukcesu.copiers.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tytanisukcesu.copiers.entity.*;
import lombok.*;

import javax.persistence.*;
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

    private Model model;

    private String serialNumber;

    private Customer customer;

    private Set<Counter> counters;

    private Address address;

    private Contract contract;

}
