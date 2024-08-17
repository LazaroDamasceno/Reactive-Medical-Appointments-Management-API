package com.api.v1.patient.dtos;

import com.api.v1.user.dtos.NewUserRequestDto;

public record NewPatientRequestDto(
        String address,
        NewUserRequestDto userDto
) {
}
