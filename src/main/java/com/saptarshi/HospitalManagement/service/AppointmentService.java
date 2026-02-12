package com.saptarshi.HospitalManagement.service;

import com.saptarshi.HospitalManagement.dto.AppointmentResponseDto;
import com.saptarshi.HospitalManagement.dto.AppointmentRequest;
import com.saptarshi.HospitalManagement.entities.Appointment;
import com.saptarshi.HospitalManagement.mapper.AppointMapper;
import com.saptarshi.HospitalManagement.repository.AppointmentRepository;
import com.saptarshi.HospitalManagement.repository.DoctorRepository;
import com.saptarshi.HospitalManagement.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final AppointMapper appointMapper;

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

    public List<AppointmentResponseDto> getAppointmentByDate(LocalDate localDate) {
        return appointmentRepository.getAppointmentsByAppointmentDate(localDate)
                .stream()
                .map(appointMapper::toResponseDto)
                .toList();
    }

    public ResponseEntity<AppointmentResponseDto> create(AppointmentRequest request) {
        var patient = patientRepository.findById(request.getPatientId()).orElse(null);
        var doctor = doctorRepository.findById(request.getDoctorId()).orElse(null);
        if (patient == null || doctor == null) {
            return ResponseEntity.badRequest().build();
        }
        Appointment appointment = new Appointment();
        appointment.setAppointmentDate(request.getAppointmentDate());
        appointment.setReason(request.getReason());
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        var saved = appointmentRepository.save(appointment);
        return ResponseEntity.ok(appointMapper.toResponseDto(saved));
    }

    public ResponseEntity<AppointmentResponseDto> update(Long id, AppointmentRequest request) {
        var existingOpt = appointmentRepository.findById(id);
        if (existingOpt.isEmpty()) return ResponseEntity.notFound().build();
        var patient = patientRepository.findById(request.getPatientId()).orElse(null);
        var doctor = doctorRepository.findById(request.getDoctorId()).orElse(null);
        if (patient == null || doctor == null) {
            return ResponseEntity.badRequest().build();
        }
        Appointment appointment = existingOpt.get();
        appointment.setAppointmentDate(request.getAppointmentDate());
        appointment.setReason(request.getReason());
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        var saved = appointmentRepository.save(appointment);
        return ResponseEntity.ok(appointMapper.toResponseDto(saved));
    }

    public ResponseEntity<Void> delete(Long id) {
        if (!appointmentRepository.existsById(id)) return ResponseEntity.notFound().build();
        appointmentRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    public List<AppointmentResponseDto> getByPatient(Long patientId) {
        return appointmentRepository.findByPatientId(patientId)
                .stream().map(appointMapper::toResponseDto)
                .toList();
    }

    public List<AppointmentResponseDto> getByDoctor(Long doctorId) {
        return appointmentRepository.findByDoctorId(doctorId)
                .stream().map(appointMapper::toResponseDto)
                .toList();
    }
}
