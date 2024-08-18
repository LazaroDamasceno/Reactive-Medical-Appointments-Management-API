package com.api.v1.doctor.builders;

import com.api.v1.doctor.domain.Doctor;
import com.api.v1.user.domain.User;

import java.util.UUID;

public class DoctorBuilder {

    private final UUID id = UUID.randomUUID();
    private String licenseNumber;
    private User user;

    protected DoctorBuilder() {}

    public static DoctorBuilder create() {
        return new DoctorBuilder();
    }

    public DoctorBuilder withLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
        return this;
    }

    public DoctorBuilder withUser(User user) {
        this.user = user;
        return this;
    }

    public Doctor build() {
        return new Doctor(id, licenseNumber, user);
    }

}
