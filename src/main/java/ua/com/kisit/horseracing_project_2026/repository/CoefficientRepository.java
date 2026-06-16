package ua.com.kisit.horseracing_project_2026.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.kisit.horseracing_project_2026.entity.Coefficients;

@Repository
public interface CoefficientRepository extends JpaRepository<Coefficients, Long> {
    // Шукає останній коефіцієнт (сортування за ID за спаданням)
    Coefficients findFirstByRaceIdAndHorseIdOrderByIdDesc(Long raceId, Long horseId);
}