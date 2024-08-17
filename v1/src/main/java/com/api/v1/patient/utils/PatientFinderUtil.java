package com.api.v1.patient.utils;

import com.api.v1.patient.domain.Patient;
import com.api.v1.patient.domain.PatientRepository;
import com.api.v1.user.annotations.SSN;
import com.api.v1.user.utils.UserFinderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class PatientFinderUtil {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private UserFinderUtil userFinderUtil;

    public Mono<Patient> find(@SSN String ssn) {
        return userFinderUtil
                .find(ssn)
                .flatMap(user -> patientRepository.findByUser(user));
    }

}