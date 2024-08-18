package com.api.v1.patient.controllers;

import com.api.v1.patient.domain.Patient;
import com.api.v1.patient.services.PatientsRetrieverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("api/v1/patients")
public class PatientsRetrieverController {

    @Autowired
    private PatientsRetrieverService service;

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public Flux<Patient> findAll() {
        return service.findAll();
    }

}
