package com.api.v1.doctor.controllers;

import com.api.v1.doctor.annotations.DLN;
import com.api.v1.doctor.domain.Doctor;
import com.api.v1.doctor.services.DoctorTerminatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/v1/doctors")
@RequiredArgsConstructor
public class DoctorTerminatorController {

    private final DoctorTerminatorService service;

    @PatchMapping("{doctorLicenseNumber}/termination")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public Mono<Doctor> terminate(@DLN String doctorLicenseNumber) {
        return service.terminate(doctorLicenseNumber);
    }

}
