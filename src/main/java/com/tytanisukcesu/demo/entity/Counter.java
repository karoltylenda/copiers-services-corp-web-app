package com.tytanisukcesu.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "counters")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Counter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate counterDate;
    private Integer monoCounter;
    private Integer colorCounter;
    private Integer totalCounter;
    private boolean isUsedToBilling;

    //FIXME - zmiana z device na model - do przejrzenia
    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Model model;

}
