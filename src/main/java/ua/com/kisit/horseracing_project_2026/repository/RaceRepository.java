package ua.com.kisit.horseracing_project_2026.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.kisit.horseracing_project_2026.entity.Races;

import java.util.List;

@Repository
public interface RaceRepository extends JpaRepository<Races, Long> {
    // Spring сам зрозуміє, що треба зробити `SELECT * FROM races WHERE status = ?`
    List<Races> findByStatus(String status);
}