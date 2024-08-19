package com.api.v1.medicalappointment.controllers;

import com.api.v1.medicalappointment.domain.MedicalAppointment;
import com.api.v1.medicalappointment.dtos.MedicalAppointmentDataRequestDto;
import com.api.v1.medicalappointment.services.MedicalAppointmentCancellingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/v1/medical-appointments")
@RequiredArgsConstructor
public class MedicalAppointmentCancellingController {

    private final MedicalAppointmentCancellingService service;

    @PatchMapping("cancellation")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    Mono<MedicalAppointment> cancel(@Valid @RequestBody MedicalAppointmentDataRequestDto dto) {
        return service.cancel(dto);
    }

}
