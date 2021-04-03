package com.tytanisukcesu.copiers.entity;

import lombok.*;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "copiersSettlement")
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class CopierSettlement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dateOfSettlement;

    private Integer startingMonoCounter;

    private Integer closingMonoCounter;

    private Integer startingColourCounter;

    private Integer closingColourCounter;

    private BigDecimal monoAmount;

    private BigDecimal colourAmount;

    private BigDecimal totalAmount;

    @ManyToOne(
            fetch = FetchType.EAGER,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST}
    )
    @JoinColumn(referencedColumnName = "id")
    @EqualsAndHashCode.Exclude
    private Contract contract;

}
