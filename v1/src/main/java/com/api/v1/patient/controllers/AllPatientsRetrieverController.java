package com.api.v1.patient.controllers;

import com.api.v1.patient.dtos.PatientResponseDto;
import com.api.v1.patient.services.AllPatientsRetrieverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("api/v1/patients")
public class AllPatientsRetrieverController {

    @Autowired
    private AllPatientsRetrieverService service;

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public Flux<PatientResponseDto> findAll() {
        return service.findAll();
    }

}
