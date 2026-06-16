package ua.com.kisit.horseracing_project_2026.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.kisit.horseracing_project_2026.bl.BetItem;
import ua.com.kisit.horseracing_project_2026.bl.BetSlip;
import ua.com.kisit.horseracing_project_2026.entity.Bets;
import ua.com.kisit.horseracing_project_2026.entity.Users;
import ua.com.kisit.horseracing_project_2026.repository.BetRepository;
import ua.com.kisit.horseracing_project_2026.repository.UserRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class BetService {

    private final BetRepository betRepository;
    private final UserRepository userRepository;

    @CacheEvict(value = "users", key = "#user.email")
    @Transactional // Обов'язково для роботи з фінансами
    public void processBetSlip(Users user, BetSlip betSlip) {

        // беремо загальну суму з твого методу getTotalAmount() і перетворюємо в BigDecimal
        BigDecimal totalAmount = new BigDecimal(betSlip.getTotalAmount());

        // Перевіряємо баланс гравця
        if (user.getBalance().compareTo(totalAmount) < 0) {
            throw new RuntimeException("Недостатньо коштів на балансі! Загальна сума: " + totalAmount + " $");
        }

        // Віднімаємо гроші та зберігаємо новий баланс
        user.setBalance(user.getBalance().subtract(totalAmount));
        userRepository.save(user);

        // Переносимо кожну ставку (сесії) в базу даних
        for (BetItem item : betSlip.getItems()) {
            Bets bet = new Bets();
            bet.setUser(user);
            bet.setRace(item.getRace());
            bet.setHorse(item.getHorse());
            bet.setAmount(new BigDecimal(item.getAmount()));
            bet.setStatus("PENDING");
            bet.setCreatedAt(LocalDateTime.now());

            betRepository.save(bet);
        }
    }
}