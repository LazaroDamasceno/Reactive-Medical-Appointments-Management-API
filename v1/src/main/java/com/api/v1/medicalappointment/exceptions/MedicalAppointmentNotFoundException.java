package com.api.v1.medicalappointment.exceptions;

public class MedicalAppointmentNotFoundException extends RuntimeException {

    public MedicalAppointmentNotFoundException() {
        super("Medical appointment was not found.");
    }

}
