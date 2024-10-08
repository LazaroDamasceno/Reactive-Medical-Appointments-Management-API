package com.api.v1.medicalappointment.controllers;

import com.api.v1.medicalappointment.dtos.MedicalAppointmentResponseDto;
import com.api.v1.medicalappointment.dtos.NewMedicalAppointmentRequestDto;
import com.api.v1.medicalappointment.interfaces.MedicalAppointmentBookingService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/v1/medical-appointments")
public class MedicalAppointmentBookingController {

    private final MedicalAppointmentBookingService service;

    public MedicalAppointmentBookingController(MedicalAppointmentBookingService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public Mono<MedicalAppointmentResponseDto> book(@Valid @RequestBody NewMedicalAppointmentRequestDto dto) {
        return service.book(dto);
    }

}
