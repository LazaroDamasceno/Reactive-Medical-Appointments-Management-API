package com.api.v1.patient.builders;

import com.api.v1.patient.domain.Patient;
import com.api.v1.user.domain.User;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
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
