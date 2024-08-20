package com.api.v1.patient.domain;

import com.api.v1.user.domain.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface PatientRepository extends ReactiveCrudRepository<Patient, UUID> {

    Mono<Patient> findByUser(User user);

}
