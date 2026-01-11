package com.saptarshi.HospitalManagement.dto;

import com.saptarshi.HospitalManagement.type.BloodGroupType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PatientDto {
    private Long id;
    private String name;
    private LocalDate birthDate;
    private String email;
    private String gender;
    private BloodGroupType bloodGroup;
    private Long insurance_id;
    private List<AppointmentDto> appointments;
}
