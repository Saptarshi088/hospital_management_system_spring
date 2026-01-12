package com.saptarshi.HospitalManagement.mapper;

import com.saptarshi.HospitalManagement.dto.AdmitPatientRequest;
import com.saptarshi.HospitalManagement.dto.PatientAppointmentDto;
import com.saptarshi.HospitalManagement.dto.PatientDto;
import com.saptarshi.HospitalManagement.dto.PatientInAppointmentResponseDto;
import com.saptarshi.HospitalManagement.entities.Appointment;
import com.saptarshi.HospitalManagement.entities.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",uses = InsuranceMapper.class)
public interface PatientMapper {
    @Mapping(source="insurance.id", target = "insurance_id")
    PatientDto toDto(Patient patient);

    @Mapping(source = "doctor.id",target = "doctor_id")
    PatientAppointmentDto toPatientAppointDto(Appointment appointment);

    PatientInAppointmentResponseDto toPatientInAppointmentDto(Patient patient);

    Patient toEntity(AdmitPatientRequest request);
}
