package com.api.v1.patient.services;

import com.api.v1.patient.domain.Patient;
import com.api.v1.patient.domain.PatientRepository;
import com.api.v1.patient.dtos.UpdatePatientRequestDto;
import com.api.v1.patient.exceptions.PatientNotFoundException;
import com.api.v1.user.annotations.SSN;
import com.api.v1.user.domain.User;
import com.api.v1.user.domain.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
class PatientUpdatingServiceImpl implements PatientUpdatingService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public Mono<Patient> update(@SSN String ssn, @Valid UpdatePatientRequestDto dto) {
        return userRepository
                .findBySsn(ssn)
                .switchIfEmpty(Mono.error(new PatientNotFoundException(ssn)))
                .flatMap(user -> {
                    User updatedUser = user.update(dto.userDto());
                    return patientRepository
                            .findByPatient(updatedUser)
                            .flatMap(patient -> {
                                Patient updatedPatient = patient.update(dto.address(), updatedUser);
                                return patientRepository.save(updatedPatient);
                            });
                });
    }

}
