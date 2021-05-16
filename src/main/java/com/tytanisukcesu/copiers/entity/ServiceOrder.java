package com.tytanisukcesu.copiers.entity;

import com.tytanisukcesu.copiers.types.ServiceOrderStatus;
import com.tytanisukcesu.copiers.types.ServiceOrderType;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "service_orders")
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class ServiceOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String serviceOrderNumber;

    private ServiceOrderType orderType;

    private ServiceOrderStatus orderStatus;

    private LocalDateTime orderCreationDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime orderStartDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime orderEndDate;

    private LocalDateTime lastUpdateDate;

    private String descriptionOfTheFault;

    @ManyToOne(
            fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE}
    )
    @JoinColumn(referencedColumnName = "id")
    private Device device;

    @OneToMany
    @EqualsAndHashCode.Exclude
    private Set<ArticleOrdered> articleOrderedSet;
}
