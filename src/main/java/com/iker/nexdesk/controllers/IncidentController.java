package com.iker.nexdesk.controllers;

import com.iker.nexdesk.dtos.RegisterIncidentRequest;
import com.iker.nexdesk.entities.Incident;
import com.iker.nexdesk.entities.Priority;
import com.iker.nexdesk.entities.State;
import com.iker.nexdesk.services.IncidentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * @author IkerOwO
 * <p>
 *     Incident Controller
 * </p>
 */
@RestController
@RequestMapping("/incident")
@CrossOrigin(origins = "*")
public class IncidentController {
    private final IncidentService service;

    public IncidentController(IncidentService service) {
        this.service = service;
    }

    @GetMapping("/getAll")
    public List<Incident> getAll() {
        return service.getAllIncidents();
    }

    @GetMapping("/{priority}")
    public List<Incident> getByPriority(@PathVariable Priority priority) {
        return service.getByPriority(priority);
    }

    @GetMapping("/{state}")
    public List<Incident> getByState(@PathVariable State state) {
        return service.getByState(state);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createIncidence(@Valid @RequestBody RegisterIncidentRequest request) {
        service.createIncidence(request);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteIncidence(@PathVariable Long id) {
        service.deleteIncidence(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
