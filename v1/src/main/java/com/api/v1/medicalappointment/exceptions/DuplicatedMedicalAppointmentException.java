package com.api.v1.medicalappointment.exceptions;

public class DuplicatedMedicalAppointmentException extends RuntimeException {

    public DuplicatedMedicalAppointmentException() {
        super("There's already one medical appointment with the input booking date.");
    }

}
