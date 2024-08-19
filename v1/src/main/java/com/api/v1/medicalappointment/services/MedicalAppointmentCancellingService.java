package com.api.v1.medicalappointment.services;

import com.api.v1.medicalappointment.domain.MedicalAppointment;
import com.api.v1.medicalappointment.dtos.ActiveAppointmentDataDto;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

public interface MedicalAppointmentCancellingService {

    Mono<MedicalAppointment> cancel(ActiveAppointmentDataDto dto);

}
