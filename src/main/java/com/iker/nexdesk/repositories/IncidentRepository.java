package com.iker.nexdesk.repositories;

import com.iker.nexdesk.entities.Incident;
import com.iker.nexdesk.entities.Priority;
import com.iker.nexdesk.entities.State;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * @author IkerOwO
 * <p>
 *     Incident Repository
 * </p>
 */
public interface IncidentRepository extends JpaRepository<Incident, Long> {
    List<Incident> getByPriority(Priority priority);
    List<Incident> getByState_of_incidence(State state);
}
