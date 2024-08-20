package com.api.v1.doctor.services;

import com.api.v1.doctor.builders.DoctorBuilder;
import com.api.v1.doctor.domain.Doctor;
import com.api.v1.doctor.domain.DoctorRepository;
import com.api.v1.doctor.dtos.DoctorResponseDto;
import com.api.v1.doctor.dtos.NewDoctorRequestDto;
import com.api.v1.doctor.mappers.DoctorResponseMapper;
import com.api.v1.doctor.utils.DoctorFinderUtil;
import com.api.v1.user.builder.UserBuilder;
import com.api.v1.user.domain.User;
import com.api.v1.user.domain.UserRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
class DoctorUpdatingServiceImpl implements DoctorUpdatingService {

    private final DoctorFinderUtil doctorFinderUtil;
    private final DoctorRepository doctorRepository;
    private final UserRepository userRepository;

    public DoctorUpdatingServiceImpl(
        DoctorFinderUtil doctorFinderUtil, 
        DoctorRepository doctorRepository,
        UserRepository userRepository
    ) {
        this.doctorFinderUtil = doctorFinderUtil;
        this.doctorRepository = doctorRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Mono<DoctorResponseDto> update(@Valid NewDoctorRequestDto dto) {
        return doctorFinderUtil
                .find(dto.doctorLicenseNumber())
                .flatMap(doctor -> {
                    User oldUser = doctor.getUser().archiveUser();
                    Mono<User> savedOldUser = userRepository.save(oldUser);
                    Doctor oldDoctor = doctor.archiveDoctor();
                    Mono<Doctor> savedOldDoctor = doctorRepository.save(oldDoctor);
                    return Mono.defer(() -> {
                        User newUser = UserBuilder.createFromDto(dto.newUserDto()).build();
                        return userRepository
                                .save(newUser)
                                .flatMap(user -> {
                                    Doctor newDoctor = DoctorBuilder
                                            .create()
                                            .withLicenseNumber(dto.doctorLicenseNumber())
                                            .withUser(user)
                                            .build();
                                    Mono<Doctor> savedNewDoctor = doctorRepository.save(newDoctor);
                                    return savedNewDoctor
                                            .flatMap(d -> Mono.just(DoctorResponseMapper.map(d)));
                                });
                    });
                });
    }

}
