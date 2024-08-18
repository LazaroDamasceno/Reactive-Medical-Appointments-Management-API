package com.api.v1.doctor.services;

import com.api.v1.doctor.domain.Doctor;
import com.api.v1.doctor.domain.DoctorRepository;
import com.api.v1.doctor.dtos.DoctorResponseDto;
import com.api.v1.doctor.mappers.DoctorResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
class DoctorsRetrievingServiceImpl implements DoctorsRetrievingService {

    private final DoctorRepository doctorRepository;

    @Override
    public Flux<DoctorResponseDto> findAll() {
        return doctorRepository
                .findAll()
                .flatMap(doctor -> Flux.just(DoctorResponseMapper.map(doctor)));
    }

    @Override
    public Flux<DoctorResponseDto> findActive() {
        return doctorRepository
                .findAll()
                .filter(e -> e.getTerminationDate() == null)
                .flatMap(doctor -> Flux.just(DoctorResponseMapper.map(doctor)));
    }

    @Override
    public Flux<DoctorResponseDto> findTerminate() {
        return doctorRepository
                .findAll()
                .filter(e -> e.getTerminationDate() != null)
                .flatMap(doctor -> Flux.just(DoctorResponseMapper.map(doctor)));
    }

}
