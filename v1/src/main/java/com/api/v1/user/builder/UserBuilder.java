package com.api.v1.user.builder;

import com.api.v1.user.domain.User;
import com.api.v1.user.dtos.NewUserRequestDto;
import jakarta.validation.Valid;

import java.time.LocalDate;
import java.util.UUID;

public class UserBuilder {

    private final UUID id;
    private final String firstName;
    private final String middleName;
    private final String lastName;
    private final LocalDate birthDate;
    private final String ssn;
    private final String email;
    private final String phoneNumber;
    private final String gender;

    private UserBuilder(
            String firstName,
            String middleName,
            String lastName,
            LocalDate birthDate,
            String ssn,
            String email,
            String phoneNumber,
            String gender
    ) {
        this.id = UUID.randomUUID();
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.ssn = ssn;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
    }

    public static UserBuilder createFromDto(@Valid NewUserRequestDto dto) {
        return new UserBuilder(
                dto.firstName(),
                dto.middleName(),
                dto.lastName(),
                dto.birthDate(),
                dto.ssn(),
                dto.email(),
                dto.phoneNumber(),
                dto.gender()
        );
    }

    public User build() {
        return new User(
                id,
                firstName,
                middleName,
                lastName,
                birthDate,
                ssn,
                email,
                phoneNumber,
                gender
        );
    }

}
