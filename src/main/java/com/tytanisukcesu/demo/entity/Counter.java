package com.tytanisukcesu.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

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
    private boolean isUsedToBilling;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Device device;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Counter)) return false;
        Counter counter = (Counter) o;
        return Objects.equals(counterDate, counter.counterDate) && Objects.equals(monoCounter, counter.monoCounter) && Objects.equals(colorCounter, counter.colorCounter) && Objects.equals(totalCounter, counter.totalCounter) && Objects.equals(device, counter.device);
    }

    @Override
    public int hashCode() {
        return Objects.hash(counterDate, monoCounter, colorCounter, totalCounter, device);
    }

}
