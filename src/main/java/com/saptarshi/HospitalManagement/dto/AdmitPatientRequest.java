package com.saptarshi.HospitalManagement.dto;

import com.saptarshi.HospitalManagement.type.BloodGroupType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class AdmitPatientRequest {

    private String name;
    private LocalDate birthDate;
    private String email;
    private String gender;
    private BloodGroupType bloodGroup;

    private AdmitPatientInsuranceDto insurance;
}
