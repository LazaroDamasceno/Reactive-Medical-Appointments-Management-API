package com.api.v1.doctor.controllers;

import com.api.v1.doctor.dtos.DoctorResponseDto;
import com.api.v1.doctor.dtos.NewDoctorRequestDto;
import com.api.v1.doctor.interfaces.DoctorHiringService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/v1/doctors")
public class DoctorHiringController {

    private final DoctorHiringService service;

    public DoctorHiringController(DoctorHiringService service) {
        this.service = service;
    }

    @PostMapping("hiring")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Mono<DoctorResponseDto> hire(@Valid @RequestBody NewDoctorRequestDto dto) {
        return service.hire(dto);
    }

}
