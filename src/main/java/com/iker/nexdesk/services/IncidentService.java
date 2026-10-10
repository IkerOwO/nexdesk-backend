package com.iker.nexdesk.services;

import com.iker.nexdesk.dtos.RegisterIncidentRequest;
import com.iker.nexdesk.entities.Incident;
import com.iker.nexdesk.entities.Priority;
import com.iker.nexdesk.entities.State;
import com.iker.nexdesk.repositories.IncidentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * @author IkerOwO
 * <p>
 *     Incident Repository
 * </p>
 */
@Service
public class IncidentService {
    private final IncidentRepository repository;

    public IncidentService(IncidentRepository repository) {
        this.repository = repository;
    }

    public List<Incident> getAllIncidents() {
        return repository.findAll();
    }

    public List<Incident> getByPriority(Priority priority) {
        return repository.getByPriority(priority);
    }

    public List<Incident> getByState(State state) {
        return repository.getByState_of_incidence(state);
    }

    public void createIncidence(RegisterIncidentRequest request) {
        //Optional<Incident> opIncident = repository.findById(id);
        //if (opIncident.isPresent()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        Incident incident = new Incident();
        incident.setTitle(request.getTitle());
        incident.setInfo(request.getInfo());
        incident.setTime_of_incidence(request.getTime_of_incidence());
        incident.setTime_of_submission(request.getTime_of_submission());
        incident.setState_of_incidence(request.getState_of_incidence());
        incident.setPriority(request.getPriority());
        repository.save(incident);
    }

    public void deleteIncidence(Long id) {
        Optional<Incident> opIncident = repository.findById(id);

        opIncident.ifPresentOrElse(
                i -> repository.delete(i),
                () -> System.out.println("Error")
        );
    }

}
