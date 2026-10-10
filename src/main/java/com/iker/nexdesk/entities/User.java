package com.iker.nexdesk.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author IkerOwO
 * <p>
 *     User Entity
 * </p>
 */
@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String email;
    private String password;
    private String role;
    private LocalDate account_created_at;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Incident> incidents = new ArrayList<>();

    public User() { }

    public User(String username, String email, String password, String role,
                LocalDate account_created_at) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.account_created_at = account_created_at;
    }

    public void addIncident(Incident incident) {
        incidents.add(incident);
        incident.setUser(this);
    }

    public void deleteIncident(Incident incident) {
        incidents.remove(incident);
        incident.setUser(null);
    }
}
