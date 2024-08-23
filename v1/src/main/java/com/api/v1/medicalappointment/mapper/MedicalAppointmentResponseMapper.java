package com.api.v1.medicalappointment.mapper;

import com.api.v1.doctor.mappers.DoctorResponseMapper;
import com.api.v1.medicalappointment.domain.MedicalAppointment;
import com.api.v1.medicalappointment.dtos.MedicalAppointmentResponseDto;
import com.api.v1.patient.mapper.PatientResponseMapper;

public class MedicalAppointmentResponseMapper {

    public static MedicalAppointmentResponseDto map(MedicalAppointment appointment) {
        return new MedicalAppointmentResponseDto(
                DoctorResponseMapper.map(appointment.getDoctor()),
                PatientResponseMapper.map(appointment.getPatient()),
                appointment.getBookingDate(),
                appointment.getCancellationDate(),
                appointment.getFinishingDate(),
                appointment.getMedicalNote(),
                appointment.getCreatedAt()
        );
    }

}
