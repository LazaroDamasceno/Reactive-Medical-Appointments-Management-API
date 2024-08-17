package com.api.v1.patient.dtos;

import com.api.v1.user.dtos.UpdateUserRequestDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record UpdatePatientRequestDto(
        @NotBlank String address,
        @Valid UpdateUserRequestDto updateUserRequestDto
) {
}
