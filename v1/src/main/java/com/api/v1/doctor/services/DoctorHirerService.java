package com.api.v1.doctor.services;

import com.api.v1.doctor.dtos.DoctorResponseDto;
import com.api.v1.doctor.dtos.NewDoctorRequestDto;
import reactor.core.publisher.Mono;

public interface DoctorHirerService {

    Mono<DoctorResponseDto> hire(NewDoctorRequestDto requestDto);

}
