package com.api.v1.patient.exceptions;

public class PatientNotFoundException extends RuntimeException {

    public PatientNotFoundException() {
        super("Patient was not found.");
    }

}
