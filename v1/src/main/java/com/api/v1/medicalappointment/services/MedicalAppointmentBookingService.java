package com.api.v1.medicalappointment.services;

import com.api.v1.medicalappointment.dtos.MedicalAppointmentResponseDto;
import com.api.v1.medicalappointment.dtos.NewMedicalAppointmentRequestDto;
import reactor.core.publisher.Mono;

public interface MedicalAppointmentBookingService {

    Mono<MedicalAppointmentResponseDto> book(NewMedicalAppointmentRequestDto dto);

}
