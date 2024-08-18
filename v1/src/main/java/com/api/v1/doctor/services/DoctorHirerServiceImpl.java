package com.api.v1.doctor.services;

import com.api.v1.doctor.DoctorResponseMapper;
import com.api.v1.doctor.builders.DoctorBuilder;
import com.api.v1.doctor.domain.Doctor;
import com.api.v1.doctor.domain.DoctorRepository;
import com.api.v1.doctor.dtos.DoctorResponseDto;
import com.api.v1.doctor.dtos.NewDoctorRequestDto;
import com.api.v1.doctor.exceptions.DuplicatedLicenseNumberException;
import com.api.v1.doctor.utils.DoctorFinderUtil;
import com.api.v1.user.builder.UserBuilder;
import com.api.v1.user.domain.User;
import com.api.v1.user.domain.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
class DoctorHirerServiceImpl implements DoctorHirerService {

    private final UserRepository userRepository;
    private final DoctorRepository doctorRepository;
    private final DoctorFinderUtil doctorFinderUtil;

    @Override
    public Mono<DoctorResponseDto> hire(@Valid NewDoctorRequestDto dto) {
        return doctorFinderUtil
                .find(dto.doctorLicenseNumber())
                .hasElement()
                .flatMap(exists -> {
                    if (exists) return handleDuplicatedLicenseNumber();
                    else {
                        User newUser = UserBuilder.createFromDto(dto.newUserDto()).build();
                        return userRepository
                                .save(newUser)
                                .flatMap(user -> {
                                    Doctor newDoctor = DoctorBuilder
                                            .create()
                                            .withUser(user)
                                            .withLicenseNumber(dto.doctorLicenseNumber())
                                            .build();
                                    return doctorRepository.save(newDoctor);
                                })
                                .flatMap(doctor -> Mono.just(DoctorResponseMapper.map(doctor)));
                }});
    }

    private Mono<DoctorResponseDto> handleDuplicatedLicenseNumber() {
        return Mono.error(DuplicatedLicenseNumberException::new);
    }

}
