package com.tytanisukcesu.copiers.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "contracts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String contractNumber;

    @OneToOne(mappedBy = "contract")
    private Device device;

    private LocalDate startDate;

    private LocalDate endDate;

    private BigDecimal monoPagePrice;

    private BigDecimal colorPagePrice;

    private BigDecimal leasePrice;

}
