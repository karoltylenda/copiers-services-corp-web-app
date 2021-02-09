package com.tytanisukcesu.copiers.dto;

import com.fasterxml.jackson.annotation.*;
import com.tytanisukcesu.copiers.entity.Device;
import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class CounterDto {

    private Long id;

    private LocalDate counterDate;

    private Integer monoCounter;

    private Integer colourCounter;

    private Integer totalCounter;

    private DeviceDto device;
}
