package com.api.v1.doctor.services;

import com.api.v1.doctor.annotations.DLN;
import com.api.v1.doctor.domain.Doctor;
import com.api.v1.doctor.domain.DoctorRepository;
import com.api.v1.doctor.interfaces.DoctorUpdatingService;
import com.api.v1.doctor.utils.DoctorFinderUtil;
import com.api.v1.user.domain.User;
import com.api.v1.user.domain.UserRepository;
import com.api.v1.user.dtos.UpdateUserRequestDto;
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
    public Mono<Doctor> update(@DLN String doctorLicenseNumber, @Valid UpdateUserRequestDto dto) {
        Mono<Doctor> doctorMono = doctorFinderUtil.find(doctorLicenseNumber);
        Mono<User> userMono = doctorMono
                .flatMap(doctor -> {
                    User archivedUser = doctor.getUser().archive();
                    return userRepository.save(archivedUser);
                })
                .flatMap(archivedUser -> {
                   User updatedUser = archivedUser.update(dto);
                   return userRepository.save(updatedUser);
                });
        return doctorMono
                .zipWith(userMono)
                .flatMap(tuple -> {
                    Doctor doctor = tuple.getT1();
                    User user = tuple.getT2();
                    doctor.update(user);
                    return doctorRepository.save(doctor);
                });
    }

}
