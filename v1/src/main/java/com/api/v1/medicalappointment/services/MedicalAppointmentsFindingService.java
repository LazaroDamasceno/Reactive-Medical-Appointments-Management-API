package com.api.v1.medicalappointment.services;

import com.api.v1.medicalappointment.dtos.MedicalAppointmentResponseDto;
import reactor.core.publisher.Flux;

public interface MedicalAppointmentsFindingService {

    Flux<MedicalAppointmentResponseDto> findAll();
    Flux<MedicalAppointmentResponseDto> findActive();
    Flux<MedicalAppointmentResponseDto> findFinished();
    Flux<MedicalAppointmentResponseDto> findCancelled();

}
