package ua.com.kisit.horseracing_project_2026.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.com.kisit.horseracing_project_2026.entity.Horses;
import ua.com.kisit.horseracing_project_2026.entity.Races;
import ua.com.kisit.horseracing_project_2026.service.AuditLogService;
import ua.com.kisit.horseracing_project_2026.service.RaceService;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/bookmaker")
@RequiredArgsConstructor
public class BookmakerController {

    private final RaceService raceService;
    private final AuditLogService auditLogService;

    // дашборд букмекера
    @GetMapping({"", "/"})
    public String bookmakerDashboard(Model model) {
        model.addAttribute("races", raceService.getRacesByStatus("Активний"));
        return "bookmaker/dashboard";
    }

    // результати
    @GetMapping("/results")
    public String bookmakerResults(Model model) {
        model.addAttribute("races", raceService.getRacesByStatus("Завершено"));
        return "bookmaker/results";
    }

    // відкриття сторінки управління конкретним забігом
    @GetMapping("/race/{id}")
    public String manageRaceDetails(@PathVariable Long id, Model model) {

        // шукаємо race id
        Races race = raceService.findById(id);

        // Передаємо
        model.addAttribute("race", race);

        // беремо коней
        Map<String, Double> coeffs = new HashMap<>();
        for (Horses horse : race.getHorses()) {
            coeffs.put(horse.getId().toString(), raceService.getLatestCoefficient(id, horse.getId()));
        }

        model.addAttribute("coeffs", coeffs);

        // повертаємо
        return "bookmaker/manage-race";
    }

    // Збереження коефіцієнтів
    @PostMapping("/race/{id}/coefficients")
    public String saveCoefficients(@PathVariable Long id, @RequestParam Map<String, String> allParams) {
        // знаходимо забіг
        Races race = raceService.findById(id);

        // перебираємо параметри з форми
        for (Map.Entry<String, String> entry : allParams.entrySet()) {
            String paramName = entry.getKey();
            String paramValue = entry.getValue();

            // Шукаємо інпути, які починаються на "coeff_"
            if (paramName.startsWith("coeff_") && !paramValue.isEmpty()) {
                // Витягуємо ID коня з назви параметра
                Long horseId = Long.parseLong(paramName.replace("coeff_", ""));
                Double coeffValue = Double.parseDouble(paramValue);

                // викликаємо сервіс, щоб він зберіг це в базу
                raceService.saveCoefficient(race.getId(), horseId, coeffValue);
            }
        }
        return "redirect:/bookmaker/race/" + id;
    }

    // Завершення забігу
    @PostMapping("/race/{id}/finish")
    public String finishRace(@PathVariable Long id, @RequestParam Long winnerId, Principal principal) {
        // завершуємо забіг
        raceService.finishRace(id, winnerId);

        // записуємо в аудит
        String username = (principal != null) ? principal.getName() : "Невідомий";
        String details = "Забіг ID: " + id + " завершено. ID коня-переможця: " + winnerId;
        auditLogService.logAction(username, "ЗАВЕРШЕННЯ ЗАБІГУ", details);

        return "redirect:/bookmaker";
    }
}