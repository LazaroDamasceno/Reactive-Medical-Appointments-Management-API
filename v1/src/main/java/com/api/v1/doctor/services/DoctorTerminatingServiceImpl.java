package com.api.v1.doctor.services;

import com.api.v1.doctor.annotations.DLN;
import com.api.v1.doctor.domain.Doctor;
import com.api.v1.doctor.domain.DoctorRepository;
import com.api.v1.doctor.utils.DoctorFinderUtil;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
class DoctorTerminatingServiceImpl implements DoctorTerminatingService {

    private final DoctorFinderUtil doctorFinderUtil;
    private final DoctorRepository doctorRepository;

    public DoctorTerminatingServiceImpl(
        DoctorFinderUtil doctorFinderUtil, 
        DoctorRepository doctorRepository
    ) {
        this.doctorFinderUtil = doctorFinderUtil;
        this.doctorRepository = doctorRepository;
    }

    @Override
    public Mono<Doctor> terminate(@DLN String doctorLicenseNumber) {
        return doctorFinderUtil
                .find(doctorLicenseNumber)
                .flatMap(doctor -> {
                    doctor.terminateDoctor();
                    return doctorRepository.save(doctor);
                });
    }

}
