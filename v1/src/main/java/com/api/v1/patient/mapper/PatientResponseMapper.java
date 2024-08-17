package com.api.v1.patient.mapper;

import com.api.v1.patient.domain.Patient;
import com.api.v1.patient.dtos.PatientResponseDto;
import com.api.v1.user.mappers.UserResponseMapper;
import jakarta.validation.constraints.NotNull;

public class PatientResponseMapper {

    public static PatientResponseDto map(@NotNull Patient patient) {
        return new PatientResponseDto(
                UserResponseMapper.map(patient.getUser()),
                patient.getAddress()
        );
    }

}
