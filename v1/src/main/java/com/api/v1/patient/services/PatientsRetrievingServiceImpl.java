package com.api.v1.patient.services;

import com.api.v1.patient.domain.Patient;
import com.api.v1.patient.domain.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
class PatientsRetrievingServiceImpl implements PatientsRetrievingService {

    private final PatientRepository repository;

    @Override
    public Flux<Patient> findAll() {
        return repository.findAll();
    }

}
