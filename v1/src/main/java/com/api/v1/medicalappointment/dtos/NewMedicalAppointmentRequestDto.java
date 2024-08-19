package com.api.v1.medicalappointment.dtos;

import com.api.v1.doctor.domain.Doctor;
import com.api.v1.patient.domain.Patient;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record NewMedicalAppointmentRequestDto(
        @NotNull Doctor doctor,
        @NotNull Patient patient,
        @NotNull LocalDateTime bookingDate
) {
}
