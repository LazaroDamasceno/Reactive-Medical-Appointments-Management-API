package com.api.v1.patient.services;

import com.api.v1.patient.domain.Patient;
import com.api.v1.patient.domain.PatientRepository;
import com.api.v1.patient.dtos.UpdatePatientRequestDto;
import com.api.v1.patient.utils.PatientFinderUtil;
import com.api.v1.user.annotations.SSN;
import com.api.v1.user.domain.User;
import com.api.v1.user.domain.UserRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
class PatientUpdatingServiceImpl implements PatientUpdatingService {

    private final UserRepository userRepository;
    private final PatientFinderUtil patientFinderUtil;
    private final PatientRepository patientRepository;

    public PatientUpdatingServiceImpl(
        UserRepository userRepository, 
        PatientFinderUtil patientFinderUtil,
        PatientRepository patientRepository
    ) {
        this.userRepository = userRepository;
        this.patientFinderUtil = patientFinderUtil;
        this.patientRepository = patientRepository;
    }

    @Override
    public Mono<Patient> update(@SSN String ssn, @Valid UpdatePatientRequestDto dto) {
        Mono<Patient> patientMono = patientFinderUtil.find(ssn);
        Mono<User> userMono = patientMono
            .flatMap(patient -> {
               User archivedUser = patient.getUser().archive();
               return userRepository.save(archivedUser); 
            })
            .flatMap(archivedUser -> {
                User updatedUser = archivedUser.update(dto.updateUserRequestDto());
                return userRepository.save(updatedUser);
            });
        return Mono.zip(patientMono, userMono)
            .flatMap(tuple -> {
                Patient patient = tuple.getT1();
                User user = tuple.getT2();
                patient.update(dto.address(), user);
                return patientRepository.save(patient);
            });
    }

}
