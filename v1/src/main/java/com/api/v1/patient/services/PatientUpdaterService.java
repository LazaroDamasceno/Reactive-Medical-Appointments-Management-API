package com.api.v1.patient.services;

import com.api.v1.patient.domain.Patient;
import com.api.v1.patient.dtos.UpdatePatientRequestDto;
import reactor.core.publisher.Mono;

public interface PatientUpdaterService {

    Mono<Patient> update(String ssn, UpdatePatientRequestDto dto);

}
