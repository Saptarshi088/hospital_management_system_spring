package com.saptarshi.HospitalManagement.service;

import com.saptarshi.HospitalManagement.dto.DoctorDto;
import com.saptarshi.HospitalManagement.dto.DoctorRequest;
import com.saptarshi.HospitalManagement.entities.Doctor;
import com.saptarshi.HospitalManagement.mapper.DoctorMapper;
import com.saptarshi.HospitalManagement.repository.AppointmentRepository;
import com.saptarshi.HospitalManagement.repository.DoctorRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final AppointmentRepository appointmentRepository;
    private final DoctorMapper doctorMapper;

    @Transactional
    public List<DoctorDto> getAll() {
        return doctorRepository.findAll()
                .stream()
                .map(doctorMapper::toDto)
                .toList();
    }

    @Transactional
    public ResponseEntity<DoctorDto> getById(Long id) {
        return doctorRepository.findById(id)
                .map(doctorMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Transactional
    public DoctorDto create(DoctorRequest request) {
        doctorRepository.findByEmail(request.getEmail()).ifPresent(d -> {
            throw new IllegalArgumentException("Doctor email already exists");
        });
        Doctor doctor = doctorMapper.toEntity(request);
        return doctorMapper.toDto(doctorRepository.save(doctor));
    }

    @Transactional
    public ResponseEntity<DoctorDto> update(Long id, DoctorRequest request) {
        var doctorOpt = doctorRepository.findById(id);
        if (doctorOpt.isEmpty()) return ResponseEntity.notFound().build();

        Doctor doctor = doctorOpt.get();
        doctorRepository.findByEmail(request.getEmail())
                .filter(existing -> !existing.getId().equals(id))
                .ifPresent(existing -> { throw new IllegalArgumentException("Doctor email already exists"); });

        doctorMapper.updateEntityFromRequest(request, doctor);
        return ResponseEntity.ok(doctorMapper.toDto(doctorRepository.save(doctor)));
    }

    @Transactional
    public ResponseEntity<Void> delete(Long id) {
        var doctorOpt = doctorRepository.findById(id);
        if (doctorOpt.isEmpty()) return ResponseEntity.notFound().build();

        // Prevent deletion if active appointments exist
        if (appointmentRepository.existsByDoctorId(id)) {
            return ResponseEntity.status(409).build();
        }

        doctorRepository.delete(doctorOpt.get());
        return ResponseEntity.noContent().build();
    }
}
