package com.api.v1.patient.services;

import com.api.v1.patient.domain.PatientRepository;
import com.api.v1.patient.dtos.PatientResponseDto;
import com.api.v1.patient.mapper.PatientResponseMapper;
import org.springframework.stereotype.Service;
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
