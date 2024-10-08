package com.api.v1.user.dtos;

import java.time.LocalDate;

public record UserResponseDto(
        String fullName,
        LocalDate birthDate,
        String ssn,
        String email,
        String phoneNumber,
        String gender
) {
}
