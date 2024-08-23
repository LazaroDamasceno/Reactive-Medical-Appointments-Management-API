package com.api.v1.patient.controllers;

import com.api.v1.patient.domain.Patient;
import com.api.v1.patient.dtos.UpdatePatientRequestDto;
import com.api.v1.patient.interfaces.PatientUpdatingService;
import com.api.v1.user.annotations.SSN;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/v1/patients")
public class PatientUpdatingController {

    private final PatientUpdatingService service;

    public PatientUpdatingController(PatientUpdatingService service) {
        this.service = service;
    }

    @PutMapping("{ssn}/updating")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public Mono<Patient> update(
            @PathVariable @SSN String ssn,
            @RequestBody @Valid UpdatePatientRequestDto dto
    ) {
        return service.update(ssn, dto);
    }

}
