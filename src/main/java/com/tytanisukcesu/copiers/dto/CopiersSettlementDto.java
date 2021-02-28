package com.tytanisukcesu.copiers.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class CopiersSettlementDto {

    private Long id;

    private LocalDate dateOfSettlement;

    private Integer startingMonoCounter;

    private Integer closingMonoCounter;

    private Integer startingColourCounter;

    private Integer closingColourCounter;

    private BigDecimal monoAmount;

    private BigDecimal colourAmount;

    private BigDecimal totalAmount;

    @EqualsAndHashCode.Exclude
    @JsonIgnoreProperties({
            "contractNumber",
            "device",
            "startDate",
            "endDate",
            "monoPagePrice",
            "colorPagePrice",
            "leasePrice",
            "initialMonoCounter",
            "initialColourCounter",
            "copierSettlementSet"
    })
    private ContractDto contract;

}
