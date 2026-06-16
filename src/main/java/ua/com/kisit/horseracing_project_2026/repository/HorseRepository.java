package ua.com.kisit.horseracing_project_2026.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.kisit.horseracing_project_2026.entity.Horses;

public interface HorseRepository extends JpaRepository<Horses, Long> {
}