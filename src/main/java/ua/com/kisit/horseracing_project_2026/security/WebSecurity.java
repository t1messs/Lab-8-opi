package ua.com.kisit.horseracing_project_2026.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurity {

    private final CustomAuthenticationSuccessHandler successHandler;

    // PasswordEncoder для шифрування паролів
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Блок Security
    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // Публічний доступ (Гості)
                        .requestMatchers("/", "/all-races", "/registration", "/login", "/css/**", "/images/**", "/js/**").permitAll()

                        // Доступ для авторизованих гравців 
                        .requestMatchers("/my-bets", "/add-bet", "/profile").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")

                        // Доступ тільки для адміністрації
                        .requestMatchers("/admin/**").hasAnyAuthority("ROLE_ADMIN")

                        // Доступ для букмекера
                        .requestMatchers("/bookmaker/**").hasAnyRole("ADMIN", "BOOKMAKER")

                        // Всі інші сторінки вимагають авторизації
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login") // Вказуємо нашу сторінку логіну
                        .usernameParameter("email") // Вказуємо Spring Security шукати поле email
                        .successHandler(successHandler) // Робимо перевірку ролі, і перенаправляємо на потрібну сторінку
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout") // Адреса, яку ловить Spring для виходу
                        .logoutSuccessUrl("/") // Куди кидаємо юзера після виходу
                        .invalidateHttpSession(true) // Знищуємо сесію
                        .clearAuthentication(true) // Очищаємо дані про авторизацію
                        .permitAll()
                );

        return http.build();
    }
}