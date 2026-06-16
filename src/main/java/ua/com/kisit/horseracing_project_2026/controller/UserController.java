package ua.com.kisit.horseracing_project_2026.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ua.com.kisit.horseracing_project_2026.entity.Users;
import ua.com.kisit.horseracing_project_2026.repository.UserRepository;
import ua.com.kisit.horseracing_project_2026.service.AuditLogService;
import ua.com.kisit.horseracing_project_2026.service.UserService;
import java.math.BigDecimal;
import java.security.Principal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final AuditLogService auditLogService;

    // Сторінка входу
    @GetMapping("/login")
    public String loginPage() {
        return "client/login";
    }

    // Сторінка реєстрації
    @GetMapping("/registration")
    public String registrationPage(Model model) {
        model.addAttribute("users", new Users());
        return "client/registration";
    }

    // сторінка профілю
    @GetMapping("/profile")
    public String showProfile(Principal principal, Model model) {
        if (principal == null) {
            return "redirect:/login"; // Якщо не авторизований - на логін
        }

        // знаходимо користувача за email і передаємо на сторінку
        Users user = userRepository.findByEmail(principal.getName());
        model.addAttribute("user", user);

        return "client/profile";
    }

    // сторінка реєстрації
    @PostMapping("/registration")
    public String registerUser(@Valid @ModelAttribute("users") Users user,
                               BindingResult bindingResult,
                               Model model) {
        // перевірка помилок
        if (bindingResult.hasErrors()) {
            return "client/registration";
        }

        // Перевірка, чи не зайнята пошта
        if (userRepository.findByEmail(user.getEmail()) != null) {
            model.addAttribute("error", "Користувач з такою поштою вже існує");
            return "client/registration";
        }

        userService.saveUser(user);
        return "redirect:/login"; // Після успішної реєстрації відправляємо на вхід
    }

    // поповнення балансу
    @PostMapping("/wallet/deposit")
    public String depositWallet(@RequestParam BigDecimal amount, Principal principal) {
        // Перевіряємо, чи користувач авторизований і чи сума більша за нуль
        if (principal != null && amount.compareTo(BigDecimal.ZERO) > 0) {
            String email = principal.getName();

            // Викликаємо метод сервісу для поповнення
            userService.depositBalance(email, amount);

            // Записуємо дію в аудит
            auditLogService.logAction(email, "ПОПОВНЕННЯ РАХУНКУ", "Користувач поповнив баланс на суму: " + amount);
        }

        // Після успішного поповнення повертаємо користувача на сторінку із забігами
        return "redirect:/all-races";
    }
}
