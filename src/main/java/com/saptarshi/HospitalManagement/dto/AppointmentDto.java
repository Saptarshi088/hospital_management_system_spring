package com.saptarshi.HospitalManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link com.saptarshi.HospitalManagement.entities.Appointment}
 */
@Getter
@Setter
@AllArgsConstructor
public class AppointmentDto  {
    Long id;
    LocalDate appointmentDate;
    String reason;
    DoctorDto doctorDto;
}