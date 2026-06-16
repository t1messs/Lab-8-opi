package ua.com.kisit.horseracing_project_2026.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Bets {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "race_id")
    private Races race;

    @ManyToOne
    @JoinColumn(name = "horse_id")
    private Horses horse;

    private BigDecimal amount; // Сума ставки (гроші)

    private String status; // Статус ставки: "FORMING", "PENDING", "WON", "LOST"

    private LocalDateTime createdAt; // Час, коли ставку було зроблено

    private Double coefficient; // Коефіцієнт
}
