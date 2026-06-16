package ua.com.kisit.horseracing_project_2026.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.kisit.horseracing_project_2026.entity.Bets;
import java.util.List;

public interface BetRepository extends JpaRepository<Bets, Long> {
    List<Bets> findAllByUserIdOrderByCreatedAtDesc(Long userId);
    List<Bets> findByRaceId(Long raceId);
}