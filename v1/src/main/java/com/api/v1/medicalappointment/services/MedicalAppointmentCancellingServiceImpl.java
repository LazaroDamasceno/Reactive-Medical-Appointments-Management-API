package com.api.v1.medicalappointment.services;

import com.api.v1.doctor.domain.Doctor;
import com.api.v1.doctor.utils.DoctorFinderUtil;
import com.api.v1.medicalappointment.domain.MedicalAppointment;
import com.api.v1.medicalappointment.domain.MedicalAppointmentRepository;
import com.api.v1.medicalappointment.dtos.MedicalAppointmentDataRequestDto;
import com.api.v1.medicalappointment.exceptions.MedicalAppointmentAlreadyCancelledException;
import com.api.v1.medicalappointment.utils.MedicalAppointmentFinderUtil;
import com.api.v1.patient.domain.Patient;
import com.api.v1.patient.utils.PatientFinderUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
class MedicalAppointmentCancellingServiceImpl implements MedicalAppointmentCancellingService {

    private final PatientFinderUtil patientFinderUtil;
    private final DoctorFinderUtil doctorFinderUtil;
    private final MedicalAppointmentFinderUtil appointmentFinderUtil;
    private final MedicalAppointmentRepository repository;

    @Override
    public Mono<MedicalAppointment> cancel(@Valid MedicalAppointmentDataRequestDto dto) {
        Mono<Patient> patientMono = patientFinderUtil.find(dto.ssn());
        Mono<Doctor> doctorMono = doctorFinderUtil.find(dto.doctorLicenseNumber());
        return doctorMono
                .zipWith(patientMono)
                .flatMap(tuple -> {
                    Patient patient = tuple.getT2();
                    Doctor doctor = tuple.getT1();
                    return appointmentFinderUtil
                            .find(doctor, patient, dto.bookedDate())
                            .filter(e -> e.getFinishingDate() != null)
                            .switchIfEmpty(Mono.error(MedicalAppointmentAlreadyCancelledException::new))
                            .flatMap(e -> {
                                e.cancelAppointment();
                                return repository.save(e);
                            });
                });
    }

}
