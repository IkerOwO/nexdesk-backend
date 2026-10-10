package com.iker.nexdesk.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.Set;

/**
 * @author IkerOwO
 * <p>
 *     Incident Entity
 * </p>
 */
@Entity
@Table(name = "incidents")
@Getter
@Setter
public class Incident {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String info;
    private LocalDate time_of_incidence;
    private LocalDate time_of_submission;
    private State state_of_incidence;
    private Priority priority;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Incident() { }

    public Incident(String title, String info, LocalDate time_of_incidence, LocalDate time_of_submission,
                    State state_of_incidence, Priority priority) {
        this.title = title;
        this.info = info;
        this.time_of_incidence = time_of_incidence;
        this.time_of_submission = time_of_submission;
        this.state_of_incidence = state_of_incidence;
        this.priority = priority;
    }
}
