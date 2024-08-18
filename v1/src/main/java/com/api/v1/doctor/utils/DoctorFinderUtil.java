package com.api.v1.doctor.utils;

import com.api.v1.doctor.annotations.DLN;
import com.api.v1.doctor.domain.Doctor;
import com.api.v1.doctor.domain.DoctorRepository;
import com.api.v1.doctor.exceptions.DoctorNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class DoctorFinderUtil {

    private final DoctorRepository doctorRepository;

    public Mono<Doctor> find(@DLN String doctorLicenseNumber) {
        return doctorRepository
                .findAll()
                .filter(e -> e.getLicenseNumber().equals(doctorLicenseNumber)
                        && e.getTerminationDate() == null
                        && e.isActive()
                )
                .singleOrEmpty()
                .switchIfEmpty(Mono.error(DoctorNotFoundException::new));
    }

}
