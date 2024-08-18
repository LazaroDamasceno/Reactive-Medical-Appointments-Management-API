package com.api.v1.doctor.services;

import com.api.v1.doctor.domain.Doctor;
import com.api.v1.user.dtos.UpdateUserRequestDto;
import reactor.core.publisher.Mono;

public interface DoctorUpdaterService {

    Mono<Doctor> update(String doctorLicenseNumber, UpdateUserRequestDto dto);

}
