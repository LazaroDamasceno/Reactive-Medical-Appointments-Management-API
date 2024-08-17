package com.api.v1.patient.domain;

import com.api.v1.user.domain.User;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface PatientRepository extends ReactiveCrudRepository<Patient, UUID> {

    Mono<Patient> findByPatient(@NotNull User user);

}
