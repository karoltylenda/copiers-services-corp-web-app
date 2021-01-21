package com.tytanisukcesu.copiers.entity;

import com.tytanisukcesu.copiers.types.ServiceOrderStatus;
import com.tytanisukcesu.copiers.types.ServiceOrderType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "service_orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String serviceOrderNumber;

    private ServiceOrderType serviceOrderType;

    private ServiceOrderStatus serviceOrderStatus;

    private LocalDateTime serviceOrderStartDate;

    private LocalDateTime serviceOrderEndDate;

    private String descriptionOfTheFault;

    @ManyToOne(
            fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE}
    )
    @JoinColumn(referencedColumnName = "id")
    private Device device;

    @OneToMany
    private Set<ArticleOrdered> articleOrderedSet;
}