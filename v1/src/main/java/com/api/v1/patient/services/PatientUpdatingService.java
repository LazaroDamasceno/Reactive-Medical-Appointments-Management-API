package com.api.v1.patient.services;

import com.api.v1.patient.dtos.PatientResponseDto;
import com.api.v1.patient.dtos.UpdatePatientRequestDto;
import reactor.core.publisher.Mono;

public interface PatientUpdatingService {

    Mono<PatientResponseDto> update(String ssn, UpdatePatientRequestDto dto);

}
