package com.api.v1.doctor;

import com.api.v1.doctor.domain.Doctor;
import com.api.v1.doctor.dtos.DoctorResponseDto;
import com.api.v1.user.mappers.UserResponseMapper;

public class DoctorResponseMapper {

    public static DoctorResponseDto map(Doctor doctor) {
        return new DoctorResponseDto(
                doctor.getLicenseNumber(),
                UserResponseMapper.map(doctor.getUser())
        );
    }

}
