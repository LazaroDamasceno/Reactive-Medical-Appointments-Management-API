package com.api.v1.doctor.dtos;

import com.api.v1.user.dtos.UserResponseDto;

public record DoctorResponseDto(
        String doctorLicenseNumber,
        UserResponseDto userDto,
        String hiringDate,
        String terminationDate
) {
}