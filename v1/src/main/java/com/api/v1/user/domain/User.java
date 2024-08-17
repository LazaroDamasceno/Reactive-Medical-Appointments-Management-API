package com.api.v1.user.domain;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.util.UUID;

@Document(collection = "v1_users")
@Getter
public class User {

    @Id
    private UUID id;

    @Field
    private String firstName;

    @Field
    private String middleName;

    @Field
    private String lastName;

    @Field
    private LocalDate birthDate;

    @Field
    private String ssn;

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
}
