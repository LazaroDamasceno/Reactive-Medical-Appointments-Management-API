package com.api.v1.medicalappointment.exceptions;

public class MedicalAppointmentAlreadyFinishedException extends RuntimeException {

    public MedicalAppointmentAlreadyFinishedException() {
        super("This medical appointment is already finished.");
    }

}
