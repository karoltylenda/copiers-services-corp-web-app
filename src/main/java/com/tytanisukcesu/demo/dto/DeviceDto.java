package com.tytanisukcesu.demo.dto;

import com.tytanisukcesu.demo.entity.Counter;
import com.tytanisukcesu.demo.entity.Customer;
import com.tytanisukcesu.demo.entity.Model;
import lombok.*;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class DeviceDto {
    private Long id;
    private Model model;
    private String serialNumber;
    private Customer customer;
    private BigDecimal monoPagePrice;
    private BigDecimal colorPagePrice;
    private Set<Counter> counters;


}
