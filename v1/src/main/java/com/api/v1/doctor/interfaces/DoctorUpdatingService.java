package com.api.v1.doctor.interfaces;

import com.api.v1.doctor.domain.Doctor;
import com.api.v1.user.dtos.UpdateUserRequestDto;
import reactor.core.publisher.Mono;

public interface DoctorUpdatingService {

    Mono<Doctor> update(String doctorLicenseNumber, UpdateUserRequestDto dto);

}
