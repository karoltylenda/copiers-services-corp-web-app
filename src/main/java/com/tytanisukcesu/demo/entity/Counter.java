package com.tytanisukcesu.demo.entity;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "counters")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Counter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate counterDate;
    private Integer monoCounter;
    private Integer colorCounter;
    private Integer totalCounter;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Device device;
}
