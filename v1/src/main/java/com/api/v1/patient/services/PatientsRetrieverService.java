package com.api.v1.patient.services;

import com.api.v1.patient.domain.Patient;
import reactor.core.publisher.Flux;

public interface PatientsRetrieverService {

    Flux<Patient> findAll();

}
