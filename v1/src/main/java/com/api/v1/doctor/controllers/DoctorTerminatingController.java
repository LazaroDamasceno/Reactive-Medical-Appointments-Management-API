package com.api.v1.doctor.controllers;

import com.api.v1.doctor.annotations.DLN;
import com.api.v1.doctor.domain.Doctor;
import com.api.v1.doctor.interfaces.DoctorTerminatingService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/v1/doctors")
public class DoctorTerminatingController {

    private final DoctorTerminatingService service;

    public DoctorTerminatingController(DoctorTerminatingService service) {
        this.service = service;
    }

    @PatchMapping("{doctorLicenseNumber}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public Mono<Doctor> terminate(@DLN String doctorLicenseNumber) {
        return service.terminate(doctorLicenseNumber);
    }

}
