package com.tytanisukcesu.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonMerge;
import lombok.*;
import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "models")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private boolean printsInColor;
    private Integer productionYear;
    @Column(nullable = false)
    private Integer printingSpeed;

    @ManyToOne(
            fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "manufacturer_id")
    @JsonIgnore
    @JsonMerge
    private Manufacturer manufacturer;

    @Column
    private PrintingFormat printingFormat;

    @OneToMany(mappedBy = "model")
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @JsonMerge
    private Set<Device> devices;

    @ManyToMany(mappedBy = "models")
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @JsonMerge
    private Set<Article> consumables;
}
