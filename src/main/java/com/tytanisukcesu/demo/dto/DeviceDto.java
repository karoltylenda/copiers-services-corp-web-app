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
public class DeviceDto {
    private Long id;
    private Model model;
    private String serialNumber;
    private Customer customer;
    private String postCode;
    private String street;
    private String city;
    private String houseNumber;
    private String apartmentNumber;
    private BigDecimal monoPagePrice;
    private BigDecimal colorPagePrice;
    private Set<Counter> counters;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DeviceDto)) return false;
        DeviceDto deviceDto = (DeviceDto) o;
        return Objects.equals(model, deviceDto.model) && Objects.equals(serialNumber, deviceDto.serialNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, serialNumber);
    }
}
