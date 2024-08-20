package com.api.v1.doctor.mappers;

import com.api.v1.doctor.domain.Doctor;
import com.api.v1.doctor.dtos.DoctorResponseDto;
import com.api.v1.user.mappers.UserResponseMapper;

public class DoctorResponseMapper {

    public static DoctorResponseDto map(Doctor doctor) {
        return new DoctorResponseDto(
                doctor.getLicenseNumber(),
                UserResponseMapper.map(doctor.getUser()),
                doctor.getHiringDate(),
                doctor.getTerminationDate()
        );
    }

}
