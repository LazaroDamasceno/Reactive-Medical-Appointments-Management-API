package com.api.v1.patient.domain;

import com.api.v1.patient.dtos.UpdatePatientRequestDto;
import com.api.v1.user.domain.User;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.UUID;

@Document(collection = "v1_patients")
public class Patient {

    @Id
    private final UUID id;

    @Field
    private String address;

    @Field
    private User user;

    public Patient(UUID id, String address, User user) {
        this.id = id;
        this.address = address;
        this.user = user;
    }

    public void update(String address, User user) {
        this.address = address;
        this.user = user;
    }

    public UUID getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public User getUser() {
        return user;
    }

}
