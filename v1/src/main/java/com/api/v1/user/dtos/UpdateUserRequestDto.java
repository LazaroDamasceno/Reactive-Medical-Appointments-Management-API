package com.api.v1.user.dtos;

import com.api.v1.user.annotations.Gender;
import com.api.v1.user.annotations.PhoneNumber;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record UpdateUserRequestDto(
        @NotBlank String firstName,
        @NotBlank String middleName,
        @NotBlank String lastName,
        @NotNull LocalDate birthDate,
        @Email @NotBlank String email,
        @PhoneNumber String phoneNumber,
        @Gender String gender
) {
}
