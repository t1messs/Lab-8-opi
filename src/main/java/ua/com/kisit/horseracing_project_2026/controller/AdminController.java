package ua.com.kisit.horseracing_project_2026.controller;

import lombok.RequiredArgsConstructor;
import java.security.Principal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.com.kisit.horseracing_project_2026.entity.Horses;
import ua.com.kisit.horseracing_project_2026.entity.Races;
import ua.com.kisit.horseracing_project_2026.repository.RoleRepository;
import ua.com.kisit.horseracing_project_2026.service.AuditLogService;
import ua.com.kisit.horseracing_project_2026.service.HorseService;
import ua.com.kisit.horseracing_project_2026.service.RaceService;
import ua.com.kisit.horseracing_project_2026.service.UserService;

@Controller
@RequestMapping("/admin") // Всі шляхи в цьому контролері будуть починатися з /admin
@RequiredArgsConstructor
public class AdminController {

    private final RaceService raceService;
    private final UserService userService;
    private final RoleRepository roleRepository;
    private final AuditLogService auditLogService;

    // Dashboard
    @GetMapping({"", "/"})
    public String adminDashboard(Model model) {
        // Тут ми можемо передавати статистику: скільки користувачів, скільки забігів
        model.addAttribute("totalRaces", raceService.getAllRaces().size());
        return "admin/dashboard";
    }

    // сторінка управління забігами
    @GetMapping("/races")
    public String manageRaces(Model model) {
        model.addAttribute("races", raceService.getAllRaces());
        return "admin/manage-races";
    }

    // Save race
    @PostMapping("/races/add")
    public String addRace(@ModelAttribute Races race, Principal principal) {
        race.setStatus("Активний"); // Ставимо статус за замовчуванням
        raceService.save(race); // Зберігаємо в базу
        String username = (principal != null) ? principal.getName() : "Адміністратор";
        auditLogService.logAction(username, "СТВОРЕННЯ ЗАБІГУ", "Створено нову подію (забіг).");
        return "redirect:/admin/races"; // Оновлюємо сторінку
    }

    // delete race
    @PostMapping("/races/delete/{id}")
    public String deleteRace(@PathVariable Long id) {
        raceService.deleteById(id); // Видаляємо з бази
        return "redirect:/admin/races";
    }

    // Редагування існуючого забігу
    @PostMapping("/races/edit")
    public String editRace(@ModelAttribute Races updatedRace) {
        // знаходимо існуючий забіг у базі за його ID
        Races existingRace = raceService.findById(updatedRace.getId());

        if (existingRace != null) {
            // Оновлюємо поля
            existingRace.setTitle(updatedRace.getTitle());
            existingRace.setEventDate(updatedRace.getEventDate());
            existingRace.setDescription(updatedRace.getDescription());

            // Зберігаємо
            raceService.save(existingRace);
        }

        return "redirect:/admin/races";
    }

    // сторінка списку користувачів
    @GetMapping("/users")
    public String manageUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        model.addAttribute("roles", roleRepository.findAll()); // щоб передати список доступних ролей
        return "admin/manage-users";
    }

    // Зміна ролі користувача
    @PostMapping("/users/role/{id}")
    public String updateUserRole(@PathVariable Long id, @RequestParam Long roleId) {
        userService.updateUserRole(id, roleId);
        return "redirect:/admin/users";
    }

    // delete user
    @PostMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return "redirect:/admin/users";
    }
    private final HorseService horseService;

    // сторінка списку коней
    @GetMapping("/horses")
    public String manageHorses(Model model) {
        model.addAttribute("horses", horseService.findAll());
        return "admin/manage-horses";
    }

    // Додавання/Редагування коня
    @PostMapping("/horses/save")
    public String saveHorse(@ModelAttribute Horses horse, Principal principal) {
        horseService.save(horse);
        String username = (principal != null) ? principal.getName() : "Адміністратор";
        auditLogService.logAction(username, "ДОДАВАННЯ КОНЯ", "Зареєстровано нового коня: " + horse.getName());
        return "redirect:/admin/horses";
    }

    // delete horse
    @PostMapping("/horses/delete/{id}")
    public String deleteHorse(@PathVariable Long id, Principal principal) {
        horseService.deleteById(id);
        String username = (principal != null) ? principal.getName() : "Адміністратор";
        auditLogService.logAction(username, "ВИДАЛЕННЯ КОНЯ", "Видалено коня з системним ID: " + id);
        return "redirect:/admin/horses";
    }

    // аудит логів у панелі адміністратора
    @GetMapping("/audit")
    public String showAuditLogs(Model model) {
        // Дістаємо всі логи і передаємо на сторінку
        model.addAttribute("logs", auditLogService.getAllLogs());
        return "admin/audit";
    }
}