package com.api.v1.patient.dtos;

import com.api.v1.user.dtos.UserResponseDto;

public record PatientResponseDto(
        UserResponseDto user,
        String address
) {
}
