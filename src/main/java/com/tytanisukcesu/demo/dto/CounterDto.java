package com.tytanisukcesu.demo.dto;

import com.tytanisukcesu.demo.entity.Model;
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

    private Integer colorCounter;

    private Integer totalCounter;

    private boolean isUsedToBilling;

    private Model model;

}
