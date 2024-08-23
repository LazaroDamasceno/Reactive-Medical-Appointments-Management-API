package com.api.v1.doctor.interfaces;

import com.api.v1.doctor.dtos.DoctorResponseDto;
import com.api.v1.doctor.dtos.NewDoctorRequestDto;
import reactor.core.publisher.Mono;

public interface DoctorHiringService {

    Mono<DoctorResponseDto> hire(NewDoctorRequestDto dto);

}
