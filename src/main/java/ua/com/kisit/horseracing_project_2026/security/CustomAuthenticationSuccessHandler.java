package ua.com.kisit.horseracing_project_2026.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    // Блок перевірки ролі користувача, після логіну
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        // Отримуємо всі ролі, які має цей користувач (з бази даних)
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        // Створюємо змінну для адреси, куди будемо перенаправляти
        String redirectUrl = "/";

        // Перевіряємо ролі
        for (GrantedAuthority authority : authorities) {
            String role = authority.getAuthority();

            if (role.equals("ROLE_ADMIN")) {
                redirectUrl = "/admin"; // Якщо адмін - на дашборд адміна
                break; // Знайшли головну роль, припиняємо пошук
            } else if (role.equals("ROLE_BOOKMAKER")) {
                redirectUrl = "/bookmaker"; // Якщо букмекер - на його дашборд
                break;
            } else if (role.equals("ROLE_USER")) {
                redirectUrl = "/all-races"; // Якщо звичайний гравець - на головну ігрову
                break;
            }
        }

        // Робимо перенаправлення
        response.sendRedirect(redirectUrl);
    }
}