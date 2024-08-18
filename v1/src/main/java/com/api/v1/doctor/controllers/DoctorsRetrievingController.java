package com.api.v1.doctor.controllers;

import com.api.v1.doctor.domain.Doctor;
import com.api.v1.doctor.dtos.DoctorResponseDto;
import com.api.v1.doctor.services.DoctorsRetrievingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("api/v1/doctors")
@RequiredArgsConstructor
public class DoctorsRetrievingController {

    private final DoctorsRetrievingService service;

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public Flux<DoctorResponseDto> findAll() {
        return service.findAll();
    }

}
