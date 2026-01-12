package com.saptarshi.HospitalManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class AdmitPatientInsuranceDto {
    private String provider;
    private String policyNumber;
    private LocalDate validUntil;

}
