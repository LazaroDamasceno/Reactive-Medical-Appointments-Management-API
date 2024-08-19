package com.api.v1.medicalappointment.dtos;

import com.api.v1.doctor.annotations.DLN;
import com.api.v1.user.annotations.SSN;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ActiveAppointmentDataDto(
        @SSN String ssn,
        @DLN String doctorLicenseNumber,
        @NotNull LocalDateTime bookedDate
) {
}
