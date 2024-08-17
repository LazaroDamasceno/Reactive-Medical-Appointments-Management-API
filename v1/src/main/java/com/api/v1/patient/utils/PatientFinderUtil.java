package com.api.v1.patient.utils;

import com.api.v1.patient.domain.Patient;
import com.api.v1.patient.domain.PatientRepository;
import com.api.v1.patient.exceptions.PatientNotFoundException;
import com.api.v1.user.annotations.SSN;
import com.api.v1.user.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class PatientFinderUtil {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private UserRepository userRepository;

    public Mono<Patient> find(@SSN String ssn) {
        return userRepository
                .findBySsn(ssn)
                .switchIfEmpty(Mono.error(new PatientNotFoundException(ssn)))
                .flatMap(user -> {
                    return patientRepository.findByPatient(user);
                });

    }

}
