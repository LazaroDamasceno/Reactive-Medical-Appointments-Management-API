package com.api.v1.patient.utils;

import com.api.v1.patient.domain.Patient;
import com.api.v1.patient.domain.PatientRepository;
import com.api.v1.patient.exceptions.PatientNotFoundException;
import com.api.v1.user.annotations.SSN;
import com.api.v1.user.utils.UserFinderUtil;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class PatientFinderUtil {

    private final PatientRepository patientRepository;
    private final UserFinderUtil userFinderUtil;

    public PatientFinderUtil(
            PatientRepository patientRepository, 
            UserFinderUtil userFinderUtil) {
        this.patientRepository = patientRepository;
        this.userFinderUtil = userFinderUtil;
    }

    public Mono<Patient> find(@SSN String ssn) {
        return userFinderUtil
                .find(ssn)
                .flatMap(patientRepository::findByUser)
                .flatMap(patient -> {
                    if (patient.getArchivingDate() != null) {
                        return Mono.error(PatientNotFoundException::new);
                    }
                    return Mono.just(patient);
                });
    }

}