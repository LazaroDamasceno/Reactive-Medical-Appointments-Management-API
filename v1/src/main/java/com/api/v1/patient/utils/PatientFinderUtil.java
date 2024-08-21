package com.api.v1.patient.utils;

import com.api.v1.patient.domain.Patient;
import com.api.v1.patient.domain.PatientRepository;
import com.api.v1.patient.exceptions.PatientNotFoundException;
import com.api.v1.user.annotations.SSN;
import com.api.v1.user.domain.UserRepository;
import com.api.v1.user.utils.UserFinderUtil;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class PatientFinderUtil {

    private final PatientRepository patientRepository;
    private final UserRepository userRepository;

    public PatientFinderUtil(
            PatientRepository patientRepository, 
            UserRepository userRepository) {
        this.patientRepository = patientRepository;
        this.userRepository = userRepository;
    }

    public Mono<Patient> find(@SSN String ssn) {
        return userRepository
                .findBySsn(ssn)
                .switchIfEmpty(Mono.error(PatientNotFoundException::new))
                .flatMap(patientRepository::findByUser);
    }

}