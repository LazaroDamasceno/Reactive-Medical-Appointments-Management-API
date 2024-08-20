package com.api.v1.doctor.domain;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.util.UUID;

public interface DoctorRepository extends ReactiveCrudRepository<Doctor, UUID> {

}
