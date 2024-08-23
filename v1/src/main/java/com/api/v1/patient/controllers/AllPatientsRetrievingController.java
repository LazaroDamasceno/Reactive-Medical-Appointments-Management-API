package com.api.v1.patient.controllers;

import com.api.v1.patient.dtos.PatientResponseDto;
import com.api.v1.patient.interfaces.AllPatientsRetrievingService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("api/v1/patients")
public class AllPatientsRetrievingController {

    private final AllPatientsRetrievingService service;

    public AllPatientsRetrievingController(AllPatientsRetrievingService service) {
        this.service = service;
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public Flux<PatientResponseDto> findAll() {
        return service.findAll();
    }

}
