package com.tytanisukcesu.copiers.dto;
import com.fasterxml.jackson.annotation.*;
import com.tytanisukcesu.copiers.entity.Device;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.OneToOne;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode

//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "id"
//)
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
