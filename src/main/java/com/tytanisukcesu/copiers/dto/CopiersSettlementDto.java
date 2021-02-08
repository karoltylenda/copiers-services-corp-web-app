package com.tytanisukcesu.copiers.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.tytanisukcesu.copiers.entity.Device;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDate;

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
public class CopiersSettlementDto {

    private Long id;

    private DeviceDto device;

    private LocalDate dateOfSettlement;

    private Integer startingMonoCounter;

    private Integer closingMonoCounter;

    private Integer startingColourCounter;

    private Integer closingColourCounter;

    private BigDecimal monoAmount;

    private BigDecimal colourAmount;

    private BigDecimal totalAmount;

}
