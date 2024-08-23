package com.api.v1.doctor.services;

import com.api.v1.doctor.mappers.DoctorResponseMapper;
import com.api.v1.doctor.builders.DoctorBuilder;
import com.api.v1.doctor.domain.Doctor;
import com.api.v1.doctor.domain.DoctorRepository;
import com.api.v1.doctor.dtos.DoctorResponseDto;
import com.api.v1.doctor.dtos.NewDoctorRequestDto;
import com.api.v1.doctor.exceptions.DuplicatedLicenseNumberException;
import com.api.v1.doctor.interfaces.DoctorHiringService;
import com.api.v1.user.builder.UserBuilder;
import com.api.v1.user.domain.User;
import com.api.v1.user.domain.UserRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
class DoctorHiringServiceImpl implements DoctorHiringService {

    private final UserRepository userRepository;
    private final DoctorRepository doctorRepository;

    public DoctorHiringServiceImpl(
        UserRepository userRepository, 
        DoctorRepository doctorRepository
    ) {
        this.userRepository = userRepository;
        this.doctorRepository = doctorRepository;
    }

    @Override
    public Mono<DoctorResponseDto> hire(@Valid NewDoctorRequestDto dto) {
        return doctorRepository
                .findAll()
                .filter(e -> e.getLicenseNumber().equals(dto.doctorLicenseNumber())
                        && e.getTerminationDate() == null
                )
                .hasElements()
                .flatMap(exists -> {
                    if (exists) return handleDuplicatedLicenseNumber();
                    else return handleHiring(dto);
                });
    }

    private Mono<DoctorResponseDto> handleDuplicatedLicenseNumber() {
        return Mono.error(DuplicatedLicenseNumberException::new);
    }

    private Mono<DoctorResponseDto> handleHiring(NewDoctorRequestDto dto) {
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
    }

}
