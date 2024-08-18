package com.api.v1.doctor.controllers;

import com.api.v1.doctor.annotations.DLN;
import com.api.v1.doctor.domain.Doctor;
import com.api.v1.doctor.services.DoctorUpdaterService;
import com.api.v1.user.dtos.UpdateUserRequestDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/v1/doctors")
@RequiredArgsConstructor
public class DoctorUpdaterController {

    private final DoctorUpdaterService service;

    @PutMapping("{doctorLicenseNumber}/updating")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public Mono<Doctor> update(
            @DLN @PathVariable String doctorLicenseNumber,
            @Valid @RequestBody UpdateUserRequestDto dto
    ) {
        return service.update(doctorLicenseNumber, dto);
    }

}
