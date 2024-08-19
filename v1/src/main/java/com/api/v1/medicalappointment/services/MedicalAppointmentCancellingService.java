package com.api.v1.medicalappointment.services;

import com.api.v1.medicalappointment.domain.MedicalAppointment;
import com.api.v1.medicalappointment.dtos.MedicalAppointmentDataRequestDto;
import reactor.core.publisher.Mono;

public interface MedicalAppointmentCancellingService {

    Mono<MedicalAppointment> cancel(MedicalAppointmentDataRequestDto dto);

}
