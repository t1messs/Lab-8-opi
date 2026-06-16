package ua.com.kisit.horseracing_project_2026.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.kisit.horseracing_project_2026.entity.Roles;
import ua.com.kisit.horseracing_project_2026.entity.Users;
import ua.com.kisit.horseracing_project_2026.repository.RoleRepository;
import ua.com.kisit.horseracing_project_2026.repository.UserRepository;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public void saveUser(Users user) {
        // Шифруємо пароль перед збереженням
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Встановлюємо початковий баланс
        user.setBalance(new java.math.BigDecimal("1000.00"));

        // Призначаємо роль за замовчуванням
        Roles userRole = roleRepository.findByName("ROLE_USER");
        if (userRole != null) {
            user.getRoles().add(userRole);
        }

        // Зберігаємо в базу
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Шукаємо користувача в базі за поштою
        Users user = userRepository.findByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException("Користувача з поштою " + email + " не знайдено.");
        }

        // Беремо ролі нашого користувача і перетворюємо їх у формат GrantedAuthority
        Collection<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());

        // Повертаємо спеціальний об'єкт User, який належить Spring Security
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                authorities
        );
    }
    // Метод для отримання всіх користувачів
    public List<Users> findAll() {
        return userRepository.findAll();
    }

    // Метод для видалення користувача за ID
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    // Метод для зміни ролі користувача
    @Transactional
    public void updateUserRole(Long userId, Long roleId) {
        // Шукаємо юзера і нову роль в базі
        Users user = userRepository.findById(userId).orElse(null);
        Roles role = roleRepository.findById(roleId).orElse(null);

        if (user != null && role != null) {
            // Очищаємо старі ролі гравця і призначаємо нову
            user.getRoles().clear();
            user.getRoles().add(role);
            userRepository.save(user);
        }
    }

    // метод для поповнення балансу
    public void depositBalance(String email, BigDecimal amount) {
        // Знаходимо користувача за логіном
        Users user = userRepository.findByEmail(email);

        // Перевіряємо, чи існує такий користувач
        if (user == null) {
            throw new RuntimeException("Користувача не знайдено");
        }

        // Якщо баланс ще порожній, ставимо 0
        if (user.getBalance() == null) {
            user.setBalance(BigDecimal.ZERO);
        }

        // Додаємо суму (через метод add, оскільки це BigDecimal)
        user.setBalance(user.getBalance().add(amount));

        // Зберігаємо оновленого користувача в базу
        userRepository.save(user);
    }
}