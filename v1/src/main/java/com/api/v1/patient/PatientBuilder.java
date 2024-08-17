package com.api.v1.patient;

import com.api.v1.patient.domain.Patient;
import com.api.v1.user.domain.User;

import java.util.UUID;

public class PatientBuilder {

    private final UUID id = UUID.randomUUID();
    private String address;
    private User user;

    public PatientBuilder withAddress(String address) {
        this.address = address;
        return this;
    }

    public PatientBuilder withUser(User user) {
        this.user = user;
        return this;
    }

    public Patient build() {
        return new Patient(id, address, user);
    }

}
