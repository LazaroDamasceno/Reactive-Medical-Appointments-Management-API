package com.api.v1.patient.services;

import org.springframework.stereotype.Service;

import com.api.v1.patient.domain.PatientRepository;
import com.api.v1.patient.dtos.PatientResponseDto;
import com.api.v1.patient.interfaces.AllPatientsRetrievingService;
import com.api.v1.patient.mapper.PatientResponseMapper;
import reactor.core.publisher.Flux;

@Service
class AllPatientsRetrievingServiceImpl implements AllPatientsRetrievingService {

    private final PatientRepository patientRepository;

    public AllPatientsRetrievingServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public Flux<PatientResponseDto> findAll() {
        return patientRepository
                .findAll()
                .flatMap(patient -> Flux.just(PatientResponseMapper.map(patient)));
    }

}
