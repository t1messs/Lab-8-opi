package ua.com.kisit.horseracing_project_2026.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Coefficients {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // До якого забігу належить цей коефіцієнт
    @ManyToOne
    @JoinColumn(name = "race_id")
    private Races race;

    // На якого коня цей коефіцієнт
    @ManyToOne
    @JoinColumn(name = "horse_id")
    private Horses horse;

    private BigDecimal multiplierValue;

    private Double value;
}