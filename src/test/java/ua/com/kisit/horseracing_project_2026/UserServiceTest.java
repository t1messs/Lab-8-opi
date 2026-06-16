package ua.com.kisit.horseracing_project_2026;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.com.kisit.horseracing_project_2026.entity.Users; // Імпорт твого правильного класу
import ua.com.kisit.horseracing_project_2026.repository.UserRepository;
import ua.com.kisit.horseracing_project_2026.service.UserService;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void testDepositBalance_Success() {
        // 1. Підготовка (Arrange)
        String email = "test@gmail.com";

        // Використовуємо Users замість User
        Users mockUser = new Users();
        mockUser.setEmail(email);
        mockUser.setBalance(new BigDecimal("50.00"));

        when(userRepository.findByEmail(email)).thenReturn(mockUser);

        // 2. Дія (Act)
        userService.depositBalance(email, new BigDecimal("100.00"));

        // 3. Перевірка (Assert)
        assertEquals(new BigDecimal("150.00"), mockUser.getBalance());

        verify(userRepository, times(1)).save(mockUser);
    }

    @Test
    public void testDepositBalance_UserNotFound() {
        // 1. Підготовка (Arrange)
        String email = "unknown@gmail.com";
        when(userRepository.findByEmail(email)).thenReturn(null);

        // 2 & 3. Дія та Перевірка
        assertThrows(RuntimeException.class, () -> {
            userService.depositBalance(email, new BigDecimal("100.00"));
        });

        // Перевіряємо, що метод save НЕ викликався
        verify(userRepository, never()).save(any(Users.class)); // Тут також замінили на Users.class
    }
}