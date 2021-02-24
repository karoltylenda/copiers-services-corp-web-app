package com.tytanisukcesu.copiers.dto;

import com.fasterxml.jackson.annotation.*;
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
public class ContractDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private String contractNumber;

    @EqualsAndHashCode.Exclude
    @JsonIgnoreProperties({
            "id",
            "model",
            "customer",
            "counters",
            "address",
            "contract"
    })
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
