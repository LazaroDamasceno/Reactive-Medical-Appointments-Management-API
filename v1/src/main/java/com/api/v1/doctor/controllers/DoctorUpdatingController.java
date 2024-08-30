package com.api.v1.doctor.controllers;

import com.api.v1.doctor.annotations.DLN;
import com.api.v1.doctor.domain.Doctor;
import com.api.v1.doctor.interfaces.DoctorUpdatingService;
import com.api.v1.user.dtos.UpdateUserRequestDto;
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

    @PutMapping("{doctorLicenseNumber}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public Mono<Doctor> update(
            @DLN @PathVariable String doctorLicenseNumber,
            @Valid @RequestBody UpdateUserRequestDto dto
    ) {
        return service.update(doctorLicenseNumber, dto);
    }

}
