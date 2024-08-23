package com.api.v1.medicalappointment.interfaces;

import com.api.v1.medicalappointment.dtos.BetweenDatesRequestDto;
import com.api.v1.medicalappointment.dtos.MedicalAppointmentResponseDto;
import reactor.core.publisher.Flux;

public interface MedicalAppointmentsFindingService {

    Flux<MedicalAppointmentResponseDto> findAll();
    Flux<MedicalAppointmentResponseDto> findActive();
    Flux<MedicalAppointmentResponseDto> findFinished();
    Flux<MedicalAppointmentResponseDto> findCancelled();
    Flux<MedicalAppointmentResponseDto> findAllByPatient(String ssn);
    Flux<MedicalAppointmentResponseDto> findActiveByPatient(String ssn);
    Flux<MedicalAppointmentResponseDto> findFinishedByPatient(String ssn);
    Flux<MedicalAppointmentResponseDto> findCancelledByPatient(String ssn);
    Flux<MedicalAppointmentResponseDto> findAllByPatient(String ssn, BetweenDatesRequestDto dates);
    Flux<MedicalAppointmentResponseDto> findActiveByPatient(String ssn, BetweenDatesRequestDto dates);
    Flux<MedicalAppointmentResponseDto> findFinishedByPatient(String ssn, BetweenDatesRequestDto dates);
    Flux<MedicalAppointmentResponseDto> findAllByDoctor(String doctorLicenseNumber);
    Flux<MedicalAppointmentResponseDto> findActiveByDoctor(String doctorLicenseNumber);
    Flux<MedicalAppointmentResponseDto> findFinishedByDoctor(String doctorLicenseNumber);
    Flux<MedicalAppointmentResponseDto> findCancelledByDoctor(String doctorLicenseNumber);
    Flux<MedicalAppointmentResponseDto> findAllByDoctor(String doctorLicenseNumber, BetweenDatesRequestDto dates);
    Flux<MedicalAppointmentResponseDto> findActiveByDoctor(String doctorLicenseNumber, BetweenDatesRequestDto dates);
    Flux<MedicalAppointmentResponseDto> findFinishedByDoctor(String doctorLicenseNumber, BetweenDatesRequestDto dates);

}
