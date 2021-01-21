package com.tytanisukcesu.copiers.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
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
    @JoinColumn(referencedColumnName = "id")
    private Customer customer;

    private LocalDate startDate;

    private LocalDate endDate;

}
