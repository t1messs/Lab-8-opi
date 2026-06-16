package ua.com.kisit.horseracing_project_2026.repository;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.kisit.horseracing_project_2026.entity.Users;

public interface UserRepository extends JpaRepository<Users, Long> {

    // пошук користувача
    @EntityGraph(attributePaths = {"roles"})
    @Cacheable(value = "users", key = "#email") // шукаємо за ключовим тегом #email
    Users findByEmail(String email);

}
