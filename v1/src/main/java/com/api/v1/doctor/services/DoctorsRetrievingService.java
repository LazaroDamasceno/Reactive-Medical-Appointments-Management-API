package com.api.v1.doctor.services;

import com.api.v1.doctor.dtos.DoctorResponseDto;
import reactor.core.publisher.Flux;

public interface DoctorsRetrievingService {

    Flux<DoctorResponseDto> findAll();

}
