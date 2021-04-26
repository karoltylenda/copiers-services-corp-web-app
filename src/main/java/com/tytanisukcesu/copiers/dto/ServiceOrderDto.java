package com.tytanisukcesu.copiers.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tytanisukcesu.copiers.entity.ArticleOrdered;
import com.tytanisukcesu.copiers.entity.Device;
import com.tytanisukcesu.copiers.types.ServiceOrderStatus;
import com.tytanisukcesu.copiers.types.ServiceOrderType;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class ServiceOrderDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private String serviceOrderNumber;

    private ServiceOrderType orderType;

    private ServiceOrderStatus orderStatus;

    private LocalDateTime orderCreationDate;

    //TODO Pola tylko edytowalne
    private LocalDateTime orderStartDate;
    //TODO Pola tylko edytowalne
    private LocalDateTime orderEndDate;

    private LocalDateTime lastUpdateDate;

    private String descriptionOfTheFault;

    @JsonIgnoreProperties({
            "id",
            "customer",
            "counters",
            "address",
            "contract"
    })
    @EqualsAndHashCode.Exclude
    private DeviceDto device;

    @JsonIgnoreProperties
    @EqualsAndHashCode.Exclude
    private Set<ArticleOrderedDto> articleOrderedSet;

}
