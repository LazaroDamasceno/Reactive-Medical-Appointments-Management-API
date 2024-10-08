package com.api.v1.patient.interfaces;

import com.api.v1.patient.dtos.PatientResponseDto;
import reactor.core.publisher.Flux;

public interface AllPatientsRetrievingService {

    Flux<PatientResponseDto> findAll();

}
