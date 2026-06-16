package ua.com.kisit.horseracing_project_2026.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.kisit.horseracing_project_2026.entity.*;
import ua.com.kisit.horseracing_project_2026.repository.*;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RaceService {

    private final RaceResultRepository raceResultRepository;
    private final CoefficientRepository coefficientRepository;
    private final BetRepository betRepository;
    private final UserRepository userRepository;
    private final HorseRepository horseRepository;
    private final RaceRepository raceRepository;

    @Cacheable("races")
    public List<Races> getAllRaces() {
        return raceRepository.findAll();
    }

    public Races findById(Long id) {
        return raceRepository.findById(id).orElse(null);
    }

    @CacheEvict(value = "races", allEntries = true)
    public void save(Races race) {
        raceRepository.save(race);
    }

    @CacheEvict(value = "races", allEntries = true)
    public void deleteById(Long id) {
        raceRepository.deleteById(id);
    }

    // Отримати забіги за конкретним статусом
    public List<Races> getRacesByStatus(String status) {
        return raceRepository.findByStatus(status);
    }

    // Збереження коефіцієнта
    @Transactional
    public void saveCoefficient(Long raceId, Long horseId, Double value) {
        // Створюємо новий запис
        Coefficients coeff = new Coefficients();
        // Встановлюємо зв'язки (race, horse) та значення
        coeff.setRace(raceRepository.findById(raceId).orElse(null));
        coeff.setHorse(horseRepository.findById(horseId).orElse(null));
        coeff.setValue(value);
        coefficientRepository.save(coeff);
    }

    // Завершення забігу
    @Transactional
    public void finishRace(Long raceId, Long winnerHorseId) {
        // Знаходимо забіг і змінюємо статус
        Races race = raceRepository.findById(raceId).orElse(null);
        if (race == null) return;

        if ("Завершено".equals(race.getStatus())) {
            System.out.println("Цей забіг вже завершено. Скасовуємо повторний запис.");
            return;
        }

        race.setStatus("Завершено");
        raceRepository.save(race);

        // Записуємо результат (у таблицю race_results)
        RaceResults result = new RaceResults();
        result.setRace(race);
        result.setHorse(horseRepository.findById(winnerHorseId).orElse(null));
        result.setPosition(1); // Перше місце
        raceResultRepository.save(result);

        // Шукаємо всі ставки на цей забіг
        List<Bets> bets = betRepository.findByRaceId(raceId);

        for (Bets bet : bets) {

            // Якщо ставка була на коня-переможця
            if (bet.getHorse().getId().equals(winnerHorseId)) {
                // Статус ставки - виграш
                bet.setStatus("Виграш");

                // Перетворюємо наш Double коефіцієнт у BigDecimal для точної математики
                BigDecimal coeffValue = BigDecimal.valueOf(bet.getCoefficient());

                // Рахуємо суму: Сума ставки (BigDecimal) помножити на Коефіцієнт (BigDecimal)
                BigDecimal winnings = bet.getAmount().multiply(coeffValue);

                // Отримуємо поточного юзера
                Users user = bet.getUser();

                // Беремо його поточний баланс
                BigDecimal currentBalance = user.getBalance() != null ? user.getBalance() : BigDecimal.ZERO;

                // Нараховуємо гроші: Поточний баланс + Виграш
                user.setBalance(currentBalance.add(winnings));

                // Зберігаємо оновленого юзера
                userRepository.save(user);
            }
            else {
                // Інакше - програш
                bet.setStatus("Програш");
            }
        }
    }

    public String getWinnerNameForRace(Long raceId) {
        // Дістаємо всі результати для цього забігу
        List<RaceResults> results = raceResultRepository.findByRaceId(raceId);

        if (results != null && !results.isEmpty()) {
            // Перебираємо результати і шукаємо того, хто на 1 місці
            for (RaceResults result : results) {
                if (result.getPosition() == 1 && result.getHorse() != null) {
                    return result.getHorse().getName(); // Повертаємо ім'я переможця
                }
            }
        }
        return null; // Якщо результатів ще немає
    }

    // Отримати актуальний коефіцієнт
    public Double getLatestCoefficient(Long raceId, Long horseId) {
        Coefficients coeff = coefficientRepository.findFirstByRaceIdAndHorseIdOrderByIdDesc(raceId, horseId);
        return coeff != null ? coeff.getValue() : 1.00;
    }
}