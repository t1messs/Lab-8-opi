package ua.com.kisit.horseracing_project_2026.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.com.kisit.horseracing_project_2026.entity.Horses;
import ua.com.kisit.horseracing_project_2026.repository.HorseRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HorseService {

    // Підключаємо репозиторій для роботи з базою
    private final HorseRepository horseRepository;

    // Отримати всіх коней
    public List<Horses> findAll() {
        return horseRepository.findAll();
    }

    // Знайти коня за ID (може знадобитися для редагування)
    public Horses findById(Long id) {
        return horseRepository.findById(id).orElse(null);
    }

    // Зберегти нового коня або оновити існуючого
    public void save(Horses horse) {
        horseRepository.save(horse);
    }

    // Видалити коня за ID
    public void deleteById(Long id) {
        horseRepository.deleteById(id);
    }
}