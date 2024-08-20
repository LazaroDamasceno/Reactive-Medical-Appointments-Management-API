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
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
class MedicalAppointmentCancellingServiceImpl implements MedicalAppointmentCancellingService {

    private final PatientFinderUtil patientFinderUtil;
    private final DoctorFinderUtil doctorFinderUtil;
    private final MedicalAppointmentFinderUtil appointmentFinderUtil;
    private final MedicalAppointmentRepository repository;

    public MedicalAppointmentCancellingServiceImpl(
        PatientFinderUtil patientFinderUtil,
        DoctorFinderUtil doctorFinderUtil, 
        MedicalAppointmentFinderUtil appointmentFinderUtil,
        MedicalAppointmentRepository repository
    ) {
        this.patientFinderUtil = patientFinderUtil;
        this.doctorFinderUtil = doctorFinderUtil;
        this.appointmentFinderUtil = appointmentFinderUtil;
        this.repository = repository;
    }

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
                            .flatMap(appointment -> {
                                if (appointment.getFinishingDate() != null) {
                                    return Mono.error(MedicalAppointmentAlreadyCancelledException::new);
                                }
                                appointment.cancelAppointment();
                                return repository.save(appointment);
                            });
                });
    }

}
