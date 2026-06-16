package ua.com.kisit.horseracing_project_2026.repository;

    // Репозиторій логування, для роботи з базою даних
import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.kisit.horseracing_project_2026.entity.AuditLog;
import java.util.List;

public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {

    List<AuditLog> findAllByOrderByTimestampDesc();
}