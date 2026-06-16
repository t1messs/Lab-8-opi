package ua.com.kisit.horseracing_project_2026.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // id

    private String username; // Імʼя користувача

    private BigDecimal balance;  // Баланс для ставок

    // Перевірка, при авторизації

    @NotBlank(message = "Пошта не може бути порожньою")
    @Email(message = "Некоректний формат пошти")
    private String email;

    @NotBlank(message = "Пароль обов'язковий")
    @Size(min = 6, message = "Пароль має бути не менше 6 символів")
    private String password;

    @NotBlank(message = "Ім'я не може бути порожнім")
    @Size(min = 2, max = 50, message = "Ім'я має містити від 2 до 50 символів")
    private String firstName;

    @NotBlank(message = "Прізвище не може бути порожнім")
    @Size(min = 2, max = 50, message = "Прізвище має містити від 2 до 50 символів")
    private String lastName;

    private String phone;

    @Min(value = 18, message = "Грати на скачках можна лише з 18 років")
    private Integer age;

    // Присвоєння ролі
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Roles> roles = new HashSet<>();

    // Встановлення балансу
    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

}