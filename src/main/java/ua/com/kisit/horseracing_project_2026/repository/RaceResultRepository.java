package ua.com.kisit.horseracing_project_2026.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.kisit.horseracing_project_2026.entity.RaceResults;

import java.util.List;

@Repository
public interface RaceResultRepository extends JpaRepository<RaceResults, Long> {
    List<RaceResults> findByRaceId(Long raceId);
}