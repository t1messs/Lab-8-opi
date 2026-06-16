package ua.com.kisit.horseracing_project_2026.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.com.kisit.horseracing_project_2026.entity.AuditLog;
import ua.com.kisit.horseracing_project_2026.repository.AuditLogRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuditLogService {

    private final AuditLogRepository auditLogRepository;

    // Головний метод для запису дії в базу
    public void logAction(String username, String action, String details) {
        AuditLog auditLog = new AuditLog();
        auditLog.setUsername(username);
        auditLog.setAction(action);
        auditLog.setDetails(details);

        auditLogRepository.save(auditLog);
    }

    // Метод для отримання історії
    public List<AuditLog> getAllLogs() {
        return auditLogRepository.findAllByOrderByTimestampDesc();
    }
}