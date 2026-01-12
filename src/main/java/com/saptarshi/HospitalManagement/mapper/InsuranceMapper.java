package com.saptarshi.HospitalManagement.mapper;

import com.saptarshi.HospitalManagement.dto.AdmitPatientInsuranceDto;
import com.saptarshi.HospitalManagement.entities.Insurance;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InsuranceMapper {
    Insurance toEntity(AdmitPatientInsuranceDto dto);
}
