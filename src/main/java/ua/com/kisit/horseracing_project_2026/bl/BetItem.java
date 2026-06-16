package ua.com.kisit.horseracing_project_2026.bl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.com.kisit.horseracing_project_2026.entity.Horses;
import ua.com.kisit.horseracing_project_2026.entity.Races;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BetItem {
    private Races race;  // Додаємо перегони, на які робимо ставку
    private Horses horse; // Додали коня
    private int amount;  // Додаємо суму ставки
}