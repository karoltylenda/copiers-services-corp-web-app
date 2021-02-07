package com.tytanisukcesu.copiers.dto;
import com.tytanisukcesu.copiers.entity.Device;
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
public class ContractDto {

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

}
