package com.api.v1.patient.dtos;

import com.api.v1.user.dtos.UpdateUserRequestDto;

public record UpdatePatientRequestDto(
        String address,
        UpdateUserRequestDto userDto
) {
}
