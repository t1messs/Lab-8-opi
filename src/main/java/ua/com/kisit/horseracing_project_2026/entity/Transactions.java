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
public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // id транзакції

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user; // user, що зробив транзакцію

    private BigDecimal amount; // Сума транзакції

    private String operationType; // тип транзакції

    private LocalDateTime createdAt; // Час проведення операції
}