package com.api.v1.medicalappointment.domain;

import com.api.v1.doctor.domain.Doctor;
import com.api.v1.patient.domain.Patient;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.ZonedDateTime;
import java.util.UUID;

@Document(collection = "v1_medical_appointments")
@Getter
public class MedicalAppointment {

    @Id
    private final UUID id;

    @Field
    private Doctor doctor;

    @Field
    private Patient patient;

    @Field
    private final String bookingDate;

    @Field
    private String cancellationDate;

    @Field
    private String finishingDate;

    @Field
    private String medicalNote;

    public MedicalAppointment(
            UUID id,
            Doctor doctor,
            Patient patient,
            String bookingDate
    ) {
        this.id = id;
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

}
