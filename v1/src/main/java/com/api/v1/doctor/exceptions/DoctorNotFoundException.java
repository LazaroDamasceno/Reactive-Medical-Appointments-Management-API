package com.api.v1.doctor.exceptions;

public class DoctorNotFoundException extends RuntimeException {

    public DoctorNotFoundException() {
        super("Doctor was not found.");
    }

}
