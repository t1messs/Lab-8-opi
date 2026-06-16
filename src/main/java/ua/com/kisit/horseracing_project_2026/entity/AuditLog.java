package ua.com.kisit.horseracing_project_2026.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "audit_logs")
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // id

    private String username; // Логін користувача
    private String action;   // назва дії
    private String details;  // Детальний опис
    private LocalDateTime timestamp; // час події

    // метод для встановлення часу, перед збереженням у базу
    @PrePersist
    protected void onCreate() {
        timestamp = LocalDateTime.now();
    }
}