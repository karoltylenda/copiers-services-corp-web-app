package com.tytanisukcesu.copiers.dto;

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

    private long id;

    private DeviceDto device;

    private LocalDate dateOfSettlement;

    private int startingMonoCounter;

    private int closingMonoCounter;

    private int startingColourCounter;

    private int closingColourCounter;

    private BigDecimal monoAmount;

    private BigDecimal colourAmount;

    private BigDecimal totalAmount;

}
