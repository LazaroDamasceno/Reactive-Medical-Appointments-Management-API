package com.api.v1.doctor.dtos;

import com.api.v1.doctor.annotations.DLN;
import com.api.v1.user.dtos.NewUserRequestDto;
import jakarta.validation.Valid;

public record NewDoctorRequestDto(
        @DLN String doctorLicenseNumber,
        @Valid NewUserRequestDto newUserDto
) {
}
