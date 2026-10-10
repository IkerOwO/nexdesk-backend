package com.iker.nexdesk.dtos;

import com.iker.nexdesk.entities.Priority;
import com.iker.nexdesk.entities.State;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class RegisterIncidentRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String info;
    private LocalDate time_of_incidence;
    private LocalDate time_of_submission;
    private State state_of_incidence;
    private Priority priority;
}
