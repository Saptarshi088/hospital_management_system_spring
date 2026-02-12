package com.saptarshi.HospitalManagement.mapper;

import com.saptarshi.HospitalManagement.dto.AppointmentDto;
import com.saptarshi.HospitalManagement.dto.AppointmentResponseDto;
import com.saptarshi.HospitalManagement.entities.Appointment;
import com.saptarshi.HospitalManagement.repository.AppointmentRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@SuppressWarnings("unused")
@Mapper(componentModel = "spring")
public interface AppointMapper {
    AppointmentDto toDto(Appointment appointment);

    @Mapping(source = "doctor", target = "doctor")
    @Mapping(source = "patient", target = "patient")
    AppointmentResponseDto toResponseDto(Appointment appointment);

    Appointment toEntity(AppointmentDto dto);

}
