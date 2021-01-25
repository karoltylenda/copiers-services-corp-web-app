package com.tytanisukcesu.copiers.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private LocalDate counterDate;

    private Integer monoCounter;

    private Integer colorCounter;

    private Integer totalCounter;

    private boolean isUsedToBilling;

    private Device device;

}
