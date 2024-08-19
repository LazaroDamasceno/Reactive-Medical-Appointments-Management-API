package com.api.v1.medicalappointment.builders;

import com.api.v1.doctor.domain.Doctor;
import com.api.v1.medicalappointment.domain.MedicalAppointment;
import com.api.v1.patient.domain.Patient;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public class MedicalAppointmentBuilder {

    private Doctor doctor;
    private Patient patient;
    private String bookingDate;

    protected MedicalAppointmentBuilder() {}

    public static  MedicalAppointmentBuilder create() {
        return new MedicalAppointmentBuilder();
    }

    public MedicalAppointmentBuilder withDoctor(Doctor doctor) {
        this.doctor = doctor;
        return this;
    }

    public MedicalAppointmentBuilder withPatient(Patient patient) {
        this.patient = patient;
        return this;
    }

    public MedicalAppointmentBuilder withBookingDate(LocalDateTime bookingDate) {
        ZonedDateTime convertedBookingDate = ZonedDateTime.of(bookingDate, ZonedDateTime.now().getZone());
        this.bookingDate = convertedBookingDate.toString();
        return this;
    }

    public MedicalAppointment build() {
        return new MedicalAppointment(
                doctor,
                patient,
                bookingDate
        );
    }

}
