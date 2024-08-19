package com.api.v1.medicalappointment.domain;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.util.UUID;

public interface MedicalAppointmentRepository extends ReactiveCrudRepository<MedicalAppointment, UUID> {
}
