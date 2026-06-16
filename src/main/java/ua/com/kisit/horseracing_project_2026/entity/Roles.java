package ua.com.kisit.horseracing_project_2026.entity;

import jakarta.persistence.*;
import lombok.*;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Roles implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // id ролі

    private String name; // назва ролі

    // присвоєння ролі
    @ManyToMany(mappedBy = "roles")
    private Set<Users> users = new HashSet<>();

    @Override
    public @Nullable String getAuthority() {
        return getName();
    }
}
