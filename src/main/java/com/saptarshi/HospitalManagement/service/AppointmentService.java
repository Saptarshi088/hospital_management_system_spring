package com.saptarshi.HospitalManagement.service;

import com.saptarshi.HospitalManagement.dto.AppointmentDto;
import com.saptarshi.HospitalManagement.dto.AppointmentResponseDto;
import com.saptarshi.HospitalManagement.entities.Appointment;
import com.saptarshi.HospitalManagement.mapper.AppointMapper;
import com.saptarshi.HospitalManagement.repository.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final AppointMapper  appointMapper;

    public List<AppointmentResponseDto> getAllAppointments() {
        return appointmentRepository.findAll()
                .stream()
                .map(appointMapper::toResponseDto)
                .toList();
    }

    public ResponseEntity<AppointmentResponseDto> getAppointmentById(Long id) {
        var appointment = appointmentRepository.findById(id).orElse(null);
        if (appointment == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(appointMapper.toResponseDto(appointment));
    }
}
