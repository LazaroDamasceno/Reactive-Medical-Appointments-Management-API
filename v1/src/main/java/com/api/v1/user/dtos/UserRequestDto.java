package com.api.v1.user.dtos;

import java.time.LocalDate;

public record UserRequestDto(
        String firstName,
        String middleName,
        String lastName,
        LocalDate birthDate,
        String ssn,
        String email,
        String phoneNumber,
        String gender
) {
}
