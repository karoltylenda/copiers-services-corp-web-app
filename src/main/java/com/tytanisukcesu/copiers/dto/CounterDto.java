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
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public class CounterDto {

    @JsonProperty("id")
    private Long id;

    private LocalDate counterDate;

    private Integer monoCounter;

    private Integer colourCounter;

    private Integer totalCounter;

    @EqualsAndHashCode.Exclude
    @JsonTypeId
    private DeviceDto device;
}
