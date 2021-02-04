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
    private long id;

    @ManyToOne(
            fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(referencedColumnName = "id")
    private Device device;

    private LocalDate dateOfSettlement;

    private int startingMonoCounter;

    private int closingMonoCounter;

    private int startingColourCounter;

    private int closingColourCounter;

    private BigDecimal monoAmount;

    private BigDecimal colourAmount;

    private BigDecimal totalAmount;

    
}
