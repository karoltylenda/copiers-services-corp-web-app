package com.tytanisukcesu.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private LocalDate counterDate;

    private Integer monoCounter;

    private Integer colorCounter;

    private Integer totalCounter;

    private boolean isUsedToBilling;

    private DeviceDto device;

}
