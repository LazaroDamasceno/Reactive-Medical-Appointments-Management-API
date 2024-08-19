package com.api.v1.medicalappointment.controllers;

import com.api.v1.medicalappointment.domain.MedicalAppointment;
import com.api.v1.medicalappointment.dtos.ActiveAppointmentDataRequestDto;
import com.api.v1.medicalappointment.dtos.MedicalNoteRequestDto;
import com.api.v1.medicalappointment.services.MedicalAppointmentFinishingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/v1/medical-appointments")
@RequiredArgsConstructor
public class MedicalAppointmentFinishingController {

    private final MedicalAppointmentFinishingService service;

    @PatchMapping("archiving")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    Mono<MedicalAppointment> finish(
            @Valid @RequestBody ActiveAppointmentDataRequestDto dataRequestDto,
            @Valid @RequestBody MedicalNoteRequestDto medicalNote
    ) {
        return service.finish(dataRequestDto, medicalNote);
    }

}
