package com.saptarshi.HospitalManagement.mapper;

import com.saptarshi.HospitalManagement.dto.AppointmentDto;
import com.saptarshi.HospitalManagement.entities.Appointment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AppointMapper {
    @Mapping(source="doctor.id", target = "doctorDto.id")
    AppointmentDto toDto(Appointment appointment);
}
