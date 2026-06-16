package ua.com.kisit.horseracing_project_2026.bl;

import lombok.Getter;
import ua.com.kisit.horseracing_project_2026.entity.Horses;
import ua.com.kisit.horseracing_project_2026.entity.Races;

import java.util.ArrayList;
import java.util.List;

@Getter
public class BetSlip {

    private List<BetItem> items = new ArrayList<>();

    // Метод додавання ставки
    public void addBet(Races race, Horses horse, int amount) {
        for (BetItem item : items) {
            // Перевіряємо, чи є вже така ставка на такого коня
            if (item.getRace().getId().equals(race.getId()) && item.getHorse().getId().equals(horse.getId())) {
                // якщо так, просто збільшуємо суму цієї ставки
                item.setAmount(item.getAmount() + amount);
                return;
            }
        }
        // Якщо на цього коня ще не ставили, створюємо новий запис
        items.add(new BetItem(race, horse, amount));
    }

    // метод видалення ставки
    public void removeBet(Races race, Horses horse) {
        items.removeIf(item ->
                item.getRace().getId().equals(race.getId()) &&
                        item.getHorse().getId().equals(horse.getId())
        );
    }

    // (Total)
    public int getTotalAmount() {
        int total = 0;
        for (BetItem item : items) {
            total += item.getAmount();
        }
        return total;
    }

    public void clear() {
        items.clear();
    }
}