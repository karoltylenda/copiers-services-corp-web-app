package com.tytanisukcesu.copiers.dto;

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

    private Long id;

    private String serviceOrderNumber;

//    @Enumerated(EnumType.STRING)
    private ServiceOrderType orderType;

//    @Enumerated(EnumType.STRING)
    private ServiceOrderStatus orderStatus;

    private LocalDateTime orderCreationDate;

    private LocalDateTime orderStartDate;

    private LocalDateTime orderEndDate;

    private LocalDateTime lastUpdateDate;

    private String descriptionOfTheFault;

    private DeviceDto device;

    private Set<ArticleOrderedDto> articleOrderedSet;

}
