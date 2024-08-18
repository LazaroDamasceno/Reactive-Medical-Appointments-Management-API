package com.api.v1.doctor.dtos;

import com.api.v1.user.dtos.NewUserRequestDto;

public record DoctorResponseDto(
        String doctorLicenseNumber,
        NewUserRequestDto userDto
) {
}