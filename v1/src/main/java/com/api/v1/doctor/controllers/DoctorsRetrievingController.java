package com.api.v1.doctor.controllers;

import com.api.v1.doctor.dtos.DoctorResponseDto;
import com.api.v1.doctor.services.DoctorsRetrievingService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("api/v1/doctors")
public class DoctorsRetrievingController {

    private final DoctorsRetrievingService service;

    public DoctorsRetrievingController(DoctorsRetrievingService service) {
        this.service = service;
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public Flux<DoctorResponseDto> findAll() {
        return service.findAll();
    }

    @GetMapping("active")
    @ResponseStatus(value = HttpStatus.OK)
    public Flux<DoctorResponseDto> findActive() {
        return service.findActive();
    }

    @GetMapping("terminated")
    @ResponseStatus(value = HttpStatus.OK)
    public Flux<DoctorResponseDto> findTerminate() {
        return service.findTerminate();
    }

}
