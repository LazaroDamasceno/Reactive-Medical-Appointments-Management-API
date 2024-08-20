package com.api.v1.patient.controllers;

import com.api.v1.patient.dtos.NewPatientRequestDto;
import com.api.v1.patient.dtos.PatientResponseDto;
import com.api.v1.patient.services.PatientUpdatingService;
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

    @PutMapping("updating")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Mono<PatientResponseDto> update(@RequestBody @Valid NewPatientRequestDto dto) {
        return service.update(dto);
    }

}
