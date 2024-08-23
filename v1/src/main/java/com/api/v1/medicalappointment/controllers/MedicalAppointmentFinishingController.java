package com.api.v1.medicalappointment.controllers;

import com.api.v1.medicalappointment.domain.MedicalAppointment;
import com.api.v1.medicalappointment.dtos.MedicalAppointmentDataRequestDto;
import com.api.v1.medicalappointment.dtos.MedicalNoteRequestDto;
import com.api.v1.medicalappointment.interfaces.MedicalAppointmentFinishingService;
import jakarta.validation.Valid;
import reactor.core.publisher.Mono;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/medical-appointments")
public class MedicalAppointmentFinishingController {

    private final MedicalAppointmentFinishingService service;

    public MedicalAppointmentFinishingController(MedicalAppointmentFinishingService service) {
        this.service = service;
    }

    @PatchMapping("finishing")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public Mono<MedicalAppointment> finish(
            @Valid @RequestBody MedicalAppointmentDataRequestDto dataRequestDto,
            @Valid @RequestBody MedicalNoteRequestDto medicalNote
    ) {
        return service.finish(dataRequestDto, medicalNote);
    }

}
