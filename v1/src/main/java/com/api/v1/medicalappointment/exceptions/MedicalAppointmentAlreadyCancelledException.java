package com.api.v1.medicalappointment.exceptions;

public class MedicalAppointmentAlreadyCancelledException extends RuntimeException {

    public MedicalAppointmentAlreadyCancelledException() {
        super("This medical appointment is already cancelled.");
    }

}
