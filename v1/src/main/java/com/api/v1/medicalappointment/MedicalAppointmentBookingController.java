package com.api.v1.medicalappointment;

import com.api.v1.medicalappointment.dtos.MedicalAppointmentResponseDto;
import com.api.v1.medicalappointment.dtos.NewMedicalAppointmentRequestDto;
import com.api.v1.medicalappointment.services.MedicalAppointmentBookingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/v1/medical-appointments")
@RequiredArgsConstructor
public class MedicalAppointmentBookingController {

    private final MedicalAppointmentBookingService service;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public Mono<MedicalAppointmentResponseDto> book(@Valid @RequestBody NewMedicalAppointmentRequestDto dto) {
        return service.book(dto);
    }

}
