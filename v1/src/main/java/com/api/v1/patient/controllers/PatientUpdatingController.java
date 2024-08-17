package com.api.v1.patient.controllers;

import com.api.v1.patient.domain.Patient;
import com.api.v1.patient.dtos.UpdatePatientRequestDto;
import com.api.v1.patient.services.PatientUpdatingService;
import com.api.v1.user.annotations.SSN;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/v1/patients")
public class PatientUpdatingController {

    @Autowired
    private PatientUpdatingService service;

    @PutMapping("{ssn}/updating")
    @ResponseStatus(value = HttpStatus.OK)
    public Mono<Patient> update(
            @SSN @PathVariable String ssn,
            @Valid @RequestBody UpdatePatientRequestDto dto
    ) {
        return service.update(ssn, dto);
    }

}
