package com.api.v1.medicalappointment.services;

import com.api.v1.doctor.domain.Doctor;
import com.api.v1.doctor.utils.DoctorFinderUtil;
import com.api.v1.medicalappointment.domain.MedicalAppointment;
import com.api.v1.medicalappointment.domain.MedicalAppointmentRepository;
import com.api.v1.medicalappointment.dtos.MedicalAppointmentDataRequestDto;
import com.api.v1.medicalappointment.dtos.MedicalNoteRequestDto;
import com.api.v1.medicalappointment.exceptions.MedicalAppointmentAlreadyFinishedException;
import com.api.v1.medicalappointment.interfaces.MedicalAppointmentFinishingService;
import com.api.v1.medicalappointment.utils.MedicalAppointmentFinderUtil;
import com.api.v1.patient.domain.Patient;
import com.api.v1.patient.utils.PatientFinderUtil;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
class MedicalAppointmentFinishingServiceImpl implements MedicalAppointmentFinishingService {

    private final MedicalAppointmentRepository repository;
    private final MedicalAppointmentFinderUtil appointmentFinderUtil;
    private final DoctorFinderUtil doctorFinderUtil;
    private final PatientFinderUtil patientFinderUtil;

    public MedicalAppointmentFinishingServiceImpl(
        MedicalAppointmentRepository repository,
        MedicalAppointmentFinderUtil appointmentFinderUtil, 
        DoctorFinderUtil doctorFinderUtil,
        PatientFinderUtil patientFinderUtil
    ) {
        this.repository = repository;
        this.appointmentFinderUtil = appointmentFinderUtil;
        this.doctorFinderUtil = doctorFinderUtil;
        this.patientFinderUtil = patientFinderUtil;
    }

    @Override
    public Mono<MedicalAppointment> finish(
            @Valid MedicalAppointmentDataRequestDto dataDto,
            @Valid MedicalNoteRequestDto medicalNote
    ) {
        Mono<Doctor> doctorMono = doctorFinderUtil.find(dataDto.doctorLicenseNumber());
        Mono<Patient> patientMono = patientFinderUtil.find(dataDto.ssn());
        return doctorMono
                .zipWith(patientMono)
                .flatMap(tuple -> {
                    Doctor doctor = tuple.getT1();
                    Patient patient = tuple.getT2();
                    return appointmentFinderUtil
                            .find(doctor, patient, dataDto.bookedDate())
                            .flatMap(appointment -> {
                                if (appointment.getCancellationDate() != null) {
                                    return Mono.error(MedicalAppointmentAlreadyFinishedException::new);
                                }
                                appointment.addMedicalNote(medicalNote.note());
                                return repository.save(appointment);
                            });
                });
    }
}
