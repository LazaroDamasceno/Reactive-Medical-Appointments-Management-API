package com.api.v1.medicalappointment.domain;

import com.api.v1.doctor.domain.Doctor;
import com.api.v1.patient.domain.Patient;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.ZonedDateTime;
import java.util.UUID;

@Document(collection = "v1_medical_appointments")
public class MedicalAppointment {

    @Id
    private final UUID id = UUID.randomUUID();

    @Field
    private final Doctor doctor;

    @Field
    private final Patient patient;

    @Field
    private final String bookingDate;

    @Field
    private String cancellationDate;

    @Field
    private String finishingDate;

    @Field
    private String medicalNote;

    @Field
    private String createdAt = ZonedDateTime.now().toString();

    public MedicalAppointment(
            Doctor doctor,
            Patient patient,
            String bookingDate
    ) {
        this.doctor = doctor;
        this.patient = patient;
        this.bookingDate = bookingDate;
    }

    public void cancelAppointment() {
        cancellationDate = ZonedDateTime.now().toString();
    }

    public void addMedicalNote(String medicalNote) {
        this.medicalNote = medicalNote;
        finishingDate = ZonedDateTime.now().toString();
    }

    public UUID getId() {
        return id;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public String getCancellationDate() {
        return cancellationDate;
    }

    public String getFinishingDate() {
        return finishingDate;
    }

    public String getMedicalNote() {
        return medicalNote;
    }

    public String getCreatedAt() {
        return createdAt;
    }
}
