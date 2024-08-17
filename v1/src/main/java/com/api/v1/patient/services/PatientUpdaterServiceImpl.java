package com.api.v1.patient.services;

import com.api.v1.patient.domain.Patient;
import com.api.v1.patient.domain.PatientRepository;
import com.api.v1.patient.dtos.UpdatePatientRequestDto;
import com.api.v1.patient.utils.PatientFinderUtil;
import com.api.v1.user.annotations.SSN;
import com.api.v1.user.domain.User;
import com.api.v1.user.domain.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
class PatientUpdaterServiceImpl implements PatientUpdaterService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PatientFinderUtil patientFinderUtil;

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public Mono<Patient> update(@SSN String ssn, @Valid UpdatePatientRequestDto dto) {
        return patientFinderUtil.find(ssn)
                .flatMap(patient -> {
                    User user = patient.getUser().update(dto.updateUserRequestDto());
                    Mono<User> updatedUser = userRepository.save(user);
                    return updatedUser
                            .zipWith(Mono.just(dto.address()))
                            .flatMap(tuple -> {
                                patient.update(tuple.getT2(), tuple.getT1());
                                return patientRepository.save(patient);
                            });
                });
    }
}
