package ua.com.kisit.horseracing_project_2026.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.kisit.horseracing_project_2026.entity.Roles;

public interface RoleRepository extends JpaRepository<Roles, Long> {
    Roles findByName(String name);
}