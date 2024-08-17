package com.api.v1.patient;

import com.api.v1.patient.dtos.NewPatientRequestDto;
import com.api.v1.patient.dtos.PatientResponseDto;
import com.api.v1.patient.services.PatientRegistrationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/v1/patients")
public class PatientRegistrationController {

    @Autowired
    private PatientRegistrationService service;

    @PostMapping("registration")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Mono<PatientResponseDto> register(@Valid @RequestBody NewPatientRequestDto dto) {
        return service.register(dto);
    }

}
