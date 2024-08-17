package com.api.v1.patient;

import com.api.v1.patient.domain.Patient;
import com.api.v1.patient.domain.PatientRepository;
import com.api.v1.user.domain.User;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class PatientFinderUtil {

    @Autowired
    private PatientRepository repository;

    public Mono<Patient> find(@NotNull User user) {
        return repository.findByPatient(user);
    }

}
