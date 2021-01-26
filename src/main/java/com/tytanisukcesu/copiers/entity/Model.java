package com.tytanisukcesu.copiers.entity;

import com.tytanisukcesu.copiers.types.PrintingFormat;
import lombok.*;
import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "models")
@Data
@AllArgsConstructor
@NoArgsConstructor
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
            cascade = CascadeType.ALL)
    @JoinColumn(name = "manufacturer_id")
    private Manufacturer manufacturer;

    private PrintingFormat printingFormat;

    @ManyToMany(mappedBy = "models")
    @EqualsAndHashCode.Exclude
    private Set<Article> consumables;
}
