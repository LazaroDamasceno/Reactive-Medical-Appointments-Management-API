package com.api.v1.doctor.services;

import com.api.v1.doctor.domain.Doctor;
import reactor.core.publisher.Mono;

public interface DoctorTerminatorService {

    Mono<Doctor> terminate(String doctorLicenseNumber);

}
