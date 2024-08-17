package com.api.v1.patient.utils;

import com.api.v1.patient.PatientNotFoundException;
import com.api.v1.patient.domain.Patient;
import com.api.v1.patient.domain.PatientRepository;
import com.api.v1.user.domain.User;
import com.api.v1.user.domain.UserRepository;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class PatientFinderUtil {

    @Autowired
    private PatientRepository repository;

    @Autowired
    private UserRepository userRepository;

    public Mono<Patient> find(@NotNull User user) {
        return userRepository
                .findBySsn(user.getSsn())
                .hasElement()
                .flatMap(exists -> {
                    if (exists) return repository.findByPatient(user);
                    else return Mono.error(new PatientNotFoundException(user.getSsn()));
                });

    }

}
