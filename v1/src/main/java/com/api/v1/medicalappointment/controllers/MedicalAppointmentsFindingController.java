package com.api.v1.medicalappointment.controllers;

import com.api.v1.doctor.annotations.DLN;
import com.api.v1.medicalappointment.dtos.BetweenDatesRequestDto;
import com.api.v1.medicalappointment.dtos.MedicalAppointmentResponseDto;
import com.api.v1.medicalappointment.interfaces.MedicalAppointmentsFindingService;
import com.api.v1.user.annotations.SSN;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("api/v1/medical-appointments")
public class MedicalAppointmentsFindingController {

    private final MedicalAppointmentsFindingService service;

    public MedicalAppointmentsFindingController(MedicalAppointmentsFindingService service) {
        this.service = service;
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public Flux<MedicalAppointmentResponseDto> findAll() {
        return service.findAll();
    }

    @GetMapping("active")
    @ResponseStatus(value = HttpStatus.OK)
    public Flux<MedicalAppointmentResponseDto> findActive() {
        return service.findActive();
    }

    @GetMapping("finished")
    @ResponseStatus(value = HttpStatus.OK)
    public Flux<MedicalAppointmentResponseDto> findFinished() {
        return service.findFinished();
    }

    @GetMapping("cancelled")
    @ResponseStatus(value = HttpStatus.OK)
    public Flux<MedicalAppointmentResponseDto> findCancelled() {
        return service.findCancelled();
    }

    @GetMapping("by-patient/{ssn}")
    @ResponseStatus(value = HttpStatus.OK)
    public Flux<MedicalAppointmentResponseDto> findAllByPatient(@SSN @PathVariable String ssn) {
        return service.findAllByPatient(ssn);
    }

    @GetMapping("active/by-patient/{ssn}")
    @ResponseStatus(value = HttpStatus.OK)
    public Flux<MedicalAppointmentResponseDto> findActiveByPatient(@SSN @PathVariable String ssn) {
        return service.findActiveByPatient(ssn);
    }

    @GetMapping("finished/by-patient/{ssn}")
    @ResponseStatus(value = HttpStatus.OK)
    public Flux<MedicalAppointmentResponseDto> findFinishedByPatient(@SSN @PathVariable String ssn) {
        return service.findFinishedByPatient(ssn);
    }

    @GetMapping("cancelled/by-patient/{ssn}")
    @ResponseStatus(value = HttpStatus.OK)
    public Flux<MedicalAppointmentResponseDto> findCancelledByPatient(@SSN @PathVariable String ssn) {
        return service.findCancelledByPatient(ssn);
    }

    @GetMapping("by-patient/{ssn}/between-dates")
    @ResponseStatus(value = HttpStatus.OK)
    public Flux<MedicalAppointmentResponseDto> findAllByPatient(@SSN @PathVariable String ssn, @Valid @RequestBody BetweenDatesRequestDto dates) {
        return service.findAllByPatient(ssn, dates);
    }

    @GetMapping("active/by-patient/{ssn}/between-dates")
    @ResponseStatus(value = HttpStatus.OK)
    public Flux<MedicalAppointmentResponseDto> findActiveByPatient(@SSN @PathVariable String ssn, @Valid @RequestBody BetweenDatesRequestDto dates) {
        return service.findActiveByPatient(ssn, dates);
    }

    @GetMapping("finished/by-patient/{ssn}/between-dates")
    @ResponseStatus(value = HttpStatus.OK)
    public Flux<MedicalAppointmentResponseDto> findFinishedByPatient(@SSN @PathVariable String ssn, @Valid @RequestBody BetweenDatesRequestDto dates) {
        return service.findFinishedByPatient(ssn, dates);
    }

    @GetMapping("by-doctor/{doctorLicenseNumber}")
    @ResponseStatus(value = HttpStatus.OK)
    public Flux<MedicalAppointmentResponseDto> findAllByDoctor(@DLN @PathVariable String doctorLicenseNumber) {
        return service.findAllByDoctor(doctorLicenseNumber);
    }

    @GetMapping("active/by-doctor/{doctorLicenseNumber}")
    @ResponseStatus(value = HttpStatus.OK)
    public Flux<MedicalAppointmentResponseDto> findActiveByDoctor(@DLN @PathVariable String doctorLicenseNumber) {
        return service.findActiveByDoctor(doctorLicenseNumber);
    }

    @GetMapping("finished/by-doctor/{doctorLicenseNumber}")
    @ResponseStatus(value = HttpStatus.OK)
    public Flux<MedicalAppointmentResponseDto> findFinishedByDoctor(@DLN @PathVariable String doctorLicenseNumber) {
        return service.findFinishedByDoctor(doctorLicenseNumber);
    }

    @GetMapping("canceled/by-doctor/{doctorLicenseNumber}")
    @ResponseStatus(value = HttpStatus.OK)
    public Flux<MedicalAppointmentResponseDto> findCancelledByDoctor(@DLN @PathVariable String doctorLicenseNumber) {
        return service.findCancelledByDoctor(doctorLicenseNumber);
    }

    @GetMapping("by-doctor/{doctorLicenseNumber}/between-dates")
    @ResponseStatus(value = HttpStatus.OK)
    public Flux<MedicalAppointmentResponseDto> findAllByDoctor(@DLN @PathVariable String doctorLicenseNumber, @Valid @RequestBody BetweenDatesRequestDto dates) {
        return service.findAllByDoctor(doctorLicenseNumber, dates);
    }

    @GetMapping("active/by-doctor/{doctorLicenseNumber}/between-dates")
    @ResponseStatus(value = HttpStatus.OK)
    public Flux<MedicalAppointmentResponseDto> findActiveByDoctor(@DLN @PathVariable String doctorLicenseNumber, @Valid @RequestBody BetweenDatesRequestDto dates) {
        return service.findActiveByDoctor(doctorLicenseNumber, dates);
    }

    @GetMapping("finished/by-doctor/{doctorLicenseNumber}/between-dates")
@   ResponseStatus(value = HttpStatus.OK)
    public Flux<MedicalAppointmentResponseDto> findFinishedByDoctor(@DLN @PathVariable String doctorLicenseNumber, @Valid @RequestBody BetweenDatesRequestDto dates) {
        return service.findFinishedByDoctor(doctorLicenseNumber, dates);
    }

}
