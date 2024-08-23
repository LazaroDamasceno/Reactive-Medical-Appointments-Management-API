package com.api.v1.medicalappointment.dtos;

import com.api.v1.doctor.dtos.DoctorResponseDto;
import com.api.v1.patient.dtos.PatientResponseDto;

public record MedicalAppointmentResponseDto(
        DoctorResponseDto doctor,
        PatientResponseDto patient,
        String bookingDate,
        String cancellationDate,
        String finishingDate,
        String medicalNote,
        String createdAt
) {
}
