package com.api.v1.doctor.interfaces;

import com.api.v1.doctor.dtos.DoctorResponseDto;
import reactor.core.publisher.Flux;

public interface DoctorsRetrievingService {

    Flux<DoctorResponseDto> findAll();
    Flux<DoctorResponseDto> findActive();
    Flux<DoctorResponseDto> findTerminate();

}
