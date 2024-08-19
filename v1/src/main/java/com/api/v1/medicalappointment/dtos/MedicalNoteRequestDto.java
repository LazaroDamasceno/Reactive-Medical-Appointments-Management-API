package com.api.v1.medicalappointment.dtos;

import jakarta.validation.constraints.NotBlank;

public record MedicalNoteRequestDto(
        @NotBlank String note
) {
}
