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
@EqualsAndHashCode
public class CounterDto {

    private Long id;
    private LocalDate counterDate;
    private Integer monoCounter;
    private Integer colorCounter;
    private Integer totalCounter;
    private boolean isUsedToBilling;
    private Device device;


}
