package ua.com.kisit.horseracing_project_2026.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ua.com.kisit.horseracing_project_2026.bl.BetSlip;
import ua.com.kisit.horseracing_project_2026.entity.Horses;
import ua.com.kisit.horseracing_project_2026.entity.Races;
import ua.com.kisit.horseracing_project_2026.entity.Users;
import ua.com.kisit.horseracing_project_2026.repository.HorseRepository;
import ua.com.kisit.horseracing_project_2026.repository.UserRepository;
import ua.com.kisit.horseracing_project_2026.service.BetService;
import ua.com.kisit.horseracing_project_2026.service.RaceService;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class BetController {

    private final RaceService raceService;
    private final HorseRepository horseRepository;
    private final BetService betService;
    private final UserRepository userRepository;

    // add bet
    @PostMapping("/add-bet")
    public String addBet(@RequestParam Long raceId, // race id
                         @RequestParam Long horseId, // Отримуємо ID коня з форми
                         @RequestParam int amount, // ставка
                         HttpSession session) {

        BetSlip betSlip = (BetSlip) session.getAttribute("betSlip");
        if (betSlip == null) {
            betSlip = new BetSlip();
            session.setAttribute("betSlip", betSlip);
        }

        // Знаходимо забіг та коня у базі
        Races race = raceService.findById(raceId);
        Horses horse = horseRepository.findById(horseId)
                .orElseThrow(() -> new RuntimeException("Коня не знайдено"));

        // add bet
        betSlip.addBet(race, horse, amount);

        return "redirect:/all-races";
    }

    // Відображення сторінки "Мої ставки"
    @GetMapping("/my-bets")
    public String showMyBets(HttpSession session, Model model) {
        BetSlip betSlip = (BetSlip) session.getAttribute("betSlip");
        if (betSlip == null) {
            betSlip = new BetSlip();
        }
        model.addAttribute("betSlip", betSlip);
        return "client/my-bets";
    }

    // Оформлення
    @PostMapping("/checkout-bets")
    public String checkoutBets(HttpSession session, Principal principal, RedirectAttributes redirectAttributes) {
        // перевірка логіну
        if (principal == null) return "redirect:/login";

        BetSlip betSlip = (BetSlip) session.getAttribute("betSlip");

        // перевірка, чи зроблена ставка
        if (betSlip == null || betSlip.getItems().isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Ви ще не зробили жодної ставки!");
            return "redirect:/my-bets";
        }

        try {
            Users user = userRepository.findByEmail(principal.getName());

            // Викликаємо сервіс, який перевірить баланс і збереже все в базу
            betService.processBetSlip(user, betSlip);

            // якщо все успішно - очищаємо сесію
            session.removeAttribute("betSlip");
            redirectAttributes.addFlashAttribute("successMessage", "Ставки успішно прийнято! Успіху!");

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }

        return "redirect:/my-bets";
    }
}