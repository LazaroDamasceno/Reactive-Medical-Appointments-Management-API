package com.api.v1.doctor.controllers;

import com.api.v1.doctor.domain.Doctor;
import com.api.v1.doctor.dtos.DoctorResponseDto;
import com.api.v1.doctor.dtos.NewDoctorRequestDto;
import com.api.v1.doctor.services.DoctorUpdatingService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/v1/doctors")
public class DoctorUpdatingController {

    private final DoctorUpdatingService service;

    public DoctorUpdatingController(DoctorUpdatingService service) {
        this.service = service;
    }

    @PutMapping("updating")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public Mono<DoctorResponseDto> update(@Valid @RequestBody NewDoctorRequestDto dto
    ) {
        return service.update(dto);
    }

}
