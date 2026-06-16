package ua.com.kisit.horseracing_project_2026.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.Set;
import java.util.HashSet;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Horses {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // id

    private String name; // Імʼя коня

    private String statsInfo; // Статистика

    private String image; // Фотографія коня

    @ManyToMany(mappedBy = "horses")
    private Set<Races> races = new HashSet<>();
}
