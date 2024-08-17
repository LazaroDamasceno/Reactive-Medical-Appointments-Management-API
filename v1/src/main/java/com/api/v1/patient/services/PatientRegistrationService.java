package com.api.v1.patient.services;

import com.api.v1.patient.dtos.NewPatientRequestDto;
import com.api.v1.patient.dtos.PatientResponseDto;
import reactor.core.publisher.Mono;

public interface PatientRegistrationService {

    Mono<PatientResponseDto> register(NewPatientRequestDto dto);

}
