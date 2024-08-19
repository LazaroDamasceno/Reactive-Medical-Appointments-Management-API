package com.api.v1.medicalappointment.utils;

import com.api.v1.doctor.domain.Doctor;
import com.api.v1.medicalappointment.domain.MedicalAppointment;
import com.api.v1.medicalappointment.domain.MedicalAppointmentRepository;
import com.api.v1.medicalappointment.exceptions.MedicalAppointmentNotFoundException;
import com.api.v1.patient.domain.Patient;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class MedicalAppointmentFinderUtil {

    private final MedicalAppointmentRepository repository;

    public Mono<MedicalAppointment> find(
            @NotNull Doctor doctor,
            @NotNull Patient patient,
            @NotNull LocalDateTime bookingDate
    ) {
        return repository
                .findAll()
                .filter(e -> e.getDoctor().equals(doctor)
                        && e.getPatient().equals(patient)
                        && e.getCancellationDate() == null
                        && e.getFinishingDate() == null
                )
                .singleOrEmpty()
                .switchIfEmpty(Mono.error(MedicalAppointmentNotFoundException::new));
    }

}
