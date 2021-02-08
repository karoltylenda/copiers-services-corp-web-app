package com.tytanisukcesu.copiers.dto;

import com.fasterxml.jackson.annotation.*;
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

//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "id"
//)
public class DeviceDto {

    private Long id;

    private ModelDto model;

    private String serialNumber;

    private CustomerDto customer;

    @EqualsAndHashCode.Exclude
    private Set<CounterDto> counters;

    private AddressDto address;

    @JsonIgnore
    private ContractDto contract;

    @EqualsAndHashCode.Exclude
    private Set<CopiersSettlementDto> copierSettlements;
}
