package com.api.v1.doctor.dtos;

import com.api.v1.doctor.DoctorLicenseNumber;
import com.api.v1.user.dtos.NewUserRequestDto;
import jakarta.validation.Valid;

public record NewDoctorRequestDto(
        @DoctorLicenseNumber String doctorLicenseNumber,
        @Valid NewUserRequestDto newUserDto
) {
}
