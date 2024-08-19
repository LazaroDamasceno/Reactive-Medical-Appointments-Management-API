package com.api.v1.medicalappointment.services;

import com.api.v1.doctor.domain.Doctor;
import com.api.v1.doctor.utils.DoctorFinderUtil;
import com.api.v1.medicalappointment.builders.MedicalAppointmentBuilder;
import com.api.v1.medicalappointment.domain.MedicalAppointment;
import com.api.v1.medicalappointment.domain.MedicalAppointmentRepository;
import com.api.v1.medicalappointment.dtos.MedicalAppointmentResponseDto;
import com.api.v1.medicalappointment.dtos.NewMedicalAppointmentRequestDto;
import com.api.v1.medicalappointment.exceptions.ConflictingDateException;
import com.api.v1.medicalappointment.mapper.MedicalAppointmentResponseMapper;
import com.api.v1.patient.domain.Patient;
import com.api.v1.patient.utils.PatientFinderUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
class MedicalAppointmentBookingServiceImpl implements MedicalAppointmentBookingService {

    private final MedicalAppointmentRepository medicalAppointmentRepository;
    private final DoctorFinderUtil doctorFinderUtil;
    private final PatientFinderUtil patientFinderUtil;

    @Override
    public Mono<MedicalAppointmentResponseDto> book(@Valid NewMedicalAppointmentRequestDto dto) {
        Mono<Doctor> doctorMono = doctorFinderUtil.find(dto.doctorLicenseNumber());
        Mono<Patient> patientMono = patientFinderUtil.find(dto.ssn());
        return Mono.defer(() -> {
            if (getToday().isEqual(getBookedDate(dto)) || getBookedDate(dto).isBefore(getToday())) {
                return handleDuplicatedBookingDate();
            }
            return handleBookingAnAppointment(doctorMono, patientMono, dto);
        });
    }

    private LocalDate getToday() {
        return LocalDate.of(
                LocalDate.now().getYear(),
                LocalDate.now().getMonthValue(),
                LocalDate.now().getDayOfMonth()
        );
    }

    private LocalDate getBookedDate(NewMedicalAppointmentRequestDto dto) {
        return LocalDate.of(
                dto.bookingDate().getYear(),
                dto.bookingDate().getMonthValue(),
                dto.bookingDate().getDayOfMonth()
        );
    }

    private Mono<MedicalAppointmentResponseDto> handleDuplicatedBookingDate() {
            return Mono.error(ConflictingDateException::new);
    }

    private Mono<MedicalAppointmentResponseDto> handleBookingAnAppointment(
            Mono<Doctor> doctorMono,
            Mono<Patient> patientMono,
            NewMedicalAppointmentRequestDto dto
    ) {
        return doctorMono
                .zipWith(patientMono)
                .flatMap(tuple -> {
                    Doctor doctor = tuple.getT1();
                    Patient patient = tuple.getT2();
                    MedicalAppointment appointment = MedicalAppointmentBuilder
                            .create()
                            .withDoctor(doctor)
                            .withPatient(patient)
                            .withBookingDate(dto.bookingDate())
                            .build();
                    Mono<MedicalAppointment> savedAppointment = medicalAppointmentRepository.save(appointment);
                    return savedAppointment.flatMap(p -> Mono.just(MedicalAppointmentResponseMapper.map(p)));
                });
    }

}
