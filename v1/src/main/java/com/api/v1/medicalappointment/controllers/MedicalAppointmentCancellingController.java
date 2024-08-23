package com.api.v1.medicalappointment.controllers;

import com.api.v1.medicalappointment.domain.MedicalAppointment;
import com.api.v1.medicalappointment.dtos.MedicalAppointmentDataRequestDto;
import com.api.v1.medicalappointment.interfaces.MedicalAppointmentCancellingService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/v1/medical-appointments")
public class MedicalAppointmentCancellingController {

    private final MedicalAppointmentCancellingService service;

    public MedicalAppointmentCancellingController(MedicalAppointmentCancellingService service) {
        this.service = service;
    }

    @PatchMapping("cancellation")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public Mono<MedicalAppointment> cancel(@Valid @RequestBody MedicalAppointmentDataRequestDto dto) {
        return service.cancel(dto);
    }

}
