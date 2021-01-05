package com.tytanisukcesu.demo.dto;

import com.tytanisukcesu.demo.entity.Device;
import lombok.*;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CounterDto {

    private Long id;
    private LocalDate counterDate;
    private Integer monoCounter;
    private Integer colorCounter;
    private Integer totalCounter;
    private boolean isUsedToBilling;
    private Device device;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CounterDto)) return false;
        CounterDto that = (CounterDto) o;
        return Objects.equals(counterDate, that.counterDate) && Objects.equals(monoCounter, that.monoCounter) && Objects.equals(colorCounter, that.colorCounter) && Objects.equals(totalCounter, that.totalCounter) && Objects.equals(device, that.device);
    }

    @Override
    public int hashCode() {
        return Objects.hash(counterDate, monoCounter, colorCounter, totalCounter, device);
    }
}
