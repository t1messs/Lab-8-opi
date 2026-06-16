package ua.com.kisit.horseracing_project_2026.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ua.com.kisit.horseracing_project_2026.entity.Horses;
import ua.com.kisit.horseracing_project_2026.entity.Races;
import ua.com.kisit.horseracing_project_2026.repository.RaceResultRepository;
import ua.com.kisit.horseracing_project_2026.service.RaceService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final RaceService raceService;
    private final RaceResultRepository raceResultRepository;

    // index.ftl
    @GetMapping("/")
    public String landingPage() {
        return "index";
    }

    // сторінка з забігами races.ftl
    @GetMapping("/all-races")
    public String getAllRacesForClient(Model model) {
        // активні забіги
        List<Races> activeRaces = raceService.getRacesByStatus("Активний");
        model.addAttribute("activeRaces", activeRaces);

        // Збираємо коефіцієнти для активних забігів
        Map<String, Double> coeffs = new HashMap<>();
        for (Races race : activeRaces) {
            for (Horses horse : race.getHorses()) {
                String key = race.getId() + "_" + horse.getId();
                coeffs.put(key, raceService.getLatestCoefficient(race.getId(), horse.getId()));
            }
        }
        model.addAttribute("coeffs", coeffs);

        // Завершені забіги
        List<Races> completedRaces = raceService.getRacesByStatus("Завершено");
        model.addAttribute("completedRaces", completedRaces);

        // перебираємо завершені забіги
        Map<String, String> winners = new HashMap<>();
        for (Races race : completedRaces) {
            // Викликаємо метод сервісу, щоб дізнатися переможця
            String winnerName = raceService.getWinnerNameForRace(race.getId());

            // перевіряємо переможця
            if (winnerName != null) {
                winners.put(String.valueOf(race.getId()), winnerName);
            }
        }
        model.addAttribute("winners", winners);

        return "client/races";
    }
}