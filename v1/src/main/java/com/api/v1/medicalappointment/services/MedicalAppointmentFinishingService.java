package com.api.v1.medicalappointment.services;

import com.api.v1.medicalappointment.domain.MedicalAppointment;
import com.api.v1.medicalappointment.dtos.MedicalAppointmentDataRequestDto;
import com.api.v1.medicalappointment.dtos.MedicalNoteRequestDto;
import reactor.core.publisher.Mono;

public interface MedicalAppointmentFinishingService {

    Mono<MedicalAppointment> finish(MedicalAppointmentDataRequestDto dataRequestDto, MedicalNoteRequestDto medicalNote);

}
