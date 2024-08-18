package com.api.v1.user.domain;

import com.api.v1.user.dtos.UpdateUserRequestDto;
import jakarta.validation.Valid;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Document(collection = "v1_users")
public class User {

    @Id
    private final UUID id;

    @Field
    private String firstName;

    @Field
    private String middleName;

    @Field
    private String lastName;

    @Field
    private LocalDate birthDate;

    @Field
    private final String ssn;

    @Field
    private String email;

    @Field
    private String phoneNumber;

    @Field
    private String gender;

    public User(
            UUID id,
            String firstName,
            String middleName,
            String lastName,
            LocalDate birthDate,
            String ssn,
            String email,
            String phoneNumber,
            String gender
    ) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.ssn = ssn;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
    }

    public User update(@Valid UpdateUserRequestDto dto) {
        this.firstName = dto.firstName();
        this.middleName = dto.middleName();
        this.lastName = dto.lastName();
        this.birthDate = dto.birthDate();
        this.email = dto.email();
        this.phoneNumber = dto.phoneNumber();
        this.gender = dto.gender();
        return this;
    }

    public String getFullName() {
        if (middleName.isEmpty()) {
            return "%s %s".formatted(firstName, lastName);
        }
        return "%s %s %s".formatted(firstName, middleName, lastName);
    }

}
