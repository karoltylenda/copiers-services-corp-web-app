package com.tytanisukcesu.copiers.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    private Integer colourCounter;

    private Integer totalCounter;

    @EqualsAndHashCode.Exclude
    @JsonIgnoreProperties({
            "id",
            "model",
            "customer",
            "counters",
            "address",
            "contract"
    })
    private DeviceDto device;
}
