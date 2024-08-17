package com.api.v1.patient.services;

import com.api.v1.patient.dtos.PatientResponseDto;
import reactor.core.publisher.Flux;

public interface FindAllPatientsService {

    Flux<PatientResponseDto> findAll();

}
