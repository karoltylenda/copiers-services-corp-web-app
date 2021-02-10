package com.tytanisukcesu.copiers.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public class ContractDto {

    @JsonProperty("id")
    private Long id;

    private String contractNumber;

    private DeviceDto device;

    private LocalDate startDate;

    private LocalDate endDate;

    private BigDecimal monoPagePrice;

    private BigDecimal colorPagePrice;

    private BigDecimal leasePrice;

    private Integer initialMonoCounter;

    private Integer initialColourCounter;

    private Set<CopiersSettlementDto> copierSettlementSet;
}
