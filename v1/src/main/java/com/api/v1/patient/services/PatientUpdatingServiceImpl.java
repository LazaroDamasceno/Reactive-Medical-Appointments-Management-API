package com.api.v1.patient.services;

import com.api.v1.patient.domain.Patient;
import com.api.v1.patient.domain.PatientRepository;
import com.api.v1.patient.dtos.PatientResponseDto;
import com.api.v1.patient.dtos.UpdatePatientRequestDto;
import com.api.v1.patient.mapper.PatientResponseMapper;
import com.api.v1.patient.utils.PatientFinderUtil;
import com.api.v1.user.annotations.SSN;
import com.api.v1.user.domain.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
class PatientUpdatingServiceImpl implements PatientUpdatingService {

    @Autowired
    private PatientFinderUtil patientFinderUtil;

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public Mono<PatientResponseDto> update(@SSN String ssn, @Valid UpdatePatientRequestDto dto) {
        return patientFinderUtil
                .find(ssn)
                .flatMap(patient -> {
                    User updatedUser = patient.getUser().update(dto.userDto());
                    patient.update(dto.address(), updatedUser);
                    return patientRepository.save(patient);
                })
                .flatMap(patient -> Mono.just(PatientResponseMapper.map(patient)));
    }

}
