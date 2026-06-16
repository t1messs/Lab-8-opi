package ua.com.kisit.horseracing_project_2026.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.HashSet;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Races {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title; // Назва забігу

    private String description; // Опис забігу

    private LocalDateTime eventDate; // Дата і час початку

    private String status; // Статус забігу

    private String image; // Плакат забігу

    @ManyToOne
    @JoinColumn(name = "winner_id")
    private Horses winner; // Поле для збереження коня-переможця

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "race_participants", // Проміжна таблиця учасників
            joinColumns = @JoinColumn(name = "race_id"),
            inverseJoinColumns = @JoinColumn(name = "horse_id")
    )
    private Set<Horses> horses = new HashSet<>();
}
