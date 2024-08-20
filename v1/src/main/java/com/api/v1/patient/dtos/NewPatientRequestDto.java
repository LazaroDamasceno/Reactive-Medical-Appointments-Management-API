package com.api.v1.patient.dtos;

import com.api.v1.user.dtos.NewUserRequestDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record NewPatientRequestDto(
        @NotBlank String address,
        @Valid NewUserRequestDto userDto
) {
}
