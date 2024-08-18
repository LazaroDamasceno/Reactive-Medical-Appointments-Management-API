package com.api.v1.patient.services;

import com.api.v1.patient.domain.Patient;
import com.api.v1.patient.domain.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
class PatientsRetrieverServiceImpl implements PatientsRetrieverService {

    @Autowired
    private PatientRepository repository;

    @Override
    public Flux<Patient> findAll() {
        return repository.findAll();
    }

}
