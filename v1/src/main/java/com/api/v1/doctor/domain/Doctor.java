package com.api.v1.doctor.domain;

import com.api.v1.user.domain.User;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.UUID;

@Document(collection = "v1_doctors")
public class Doctor {

    @Id
    private final UUID id;

    @Field
    private final String licenseNumber;

    @Field
    private User user;

    public Doctor(UUID id, String licenseNumber, User user) {
        this.id = id;
        this.licenseNumber = licenseNumber;
        this.user = user;
    }

    public UUID getId() {
        return id;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public User getUser() {
        return user;
    }

}
