package com.saptarshi.HospitalManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class PatientAppointmentDto {

    private Long id;


    private LocalDate appointmentDate;


    private String reason;

    private Long doctor_id;
}
