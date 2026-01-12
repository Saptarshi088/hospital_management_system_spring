package com.saptarshi.HospitalManagement.dto;

import com.saptarshi.HospitalManagement.entities.Doctor;
import com.saptarshi.HospitalManagement.entities.Patient;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class AppointmentResponseDto {
    private Long id;

    private LocalDate appointmentDate;

    private String reason;

    private PatientInAppointmentResponseDto patient;

    private DoctorDto doctor;
}
