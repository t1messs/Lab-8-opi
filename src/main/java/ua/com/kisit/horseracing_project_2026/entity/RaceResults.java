package ua.com.kisit.horseracing_project_2026.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RaceResults {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "race_id")
    private Races race;

    // Хто саме переміг у цьому забігу
    @ManyToOne
    @JoinColumn(name = "winner_horse_id")
    private Horses winnerHorse;

    @ManyToOne
    @JoinColumn(name = "horse_id")
    private Horses horse;

    private Integer position;
}