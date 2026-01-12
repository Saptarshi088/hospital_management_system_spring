package com.saptarshi.HospitalManagement.service;

import com.saptarshi.HospitalManagement.dto.AdmitPatientInsuranceDto;
import com.saptarshi.HospitalManagement.dto.AdmitPatientRequest;
import com.saptarshi.HospitalManagement.dto.PatientAppointmentDto;
import com.saptarshi.HospitalManagement.dto.PatientDto;
import com.saptarshi.HospitalManagement.entities.Insurance;
import com.saptarshi.HospitalManagement.entities.Patient;
import com.saptarshi.HospitalManagement.exceptions.DuplicateInsurancePolicyException;
import com.saptarshi.HospitalManagement.mapper.AppointMapper;
import com.saptarshi.HospitalManagement.mapper.PatientMapper;
import com.saptarshi.HospitalManagement.repository.AppointmentRepository;
import com.saptarshi.HospitalManagement.repository.InsuranceRepository;
import com.saptarshi.HospitalManagement.repository.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
@AllArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;
    private final AppointmentRepository appointmentRepository;
    private final AppointMapper  appointMapper;
    private final InsuranceRepository insuranceRepository;

    @Transactional
    public List<PatientDto> getAllPatients() {
        return patientRepository.findAll()
                .stream()
                .map(patientMapper::toDto)
                .toList();
    }

    @Transactional
    public ResponseEntity<PatientDto> findPatientById(Long id) {
        var patient = patientRepository.findById(id).orElse(null);
        if (patient == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(patientMapper.toDto(patient));
    }

    public List<PatientAppointmentDto> findPatientAppointments(Long id) {
        return appointmentRepository.findByPatientId(id)
                .stream().map(patientMapper::toPatientAppointDto)
                .toList();
    }

    @Transactional
    public PatientDto admitPatient(AdmitPatientRequest request) {

        AdmitPatientInsuranceDto insuranceDto = request.getInsurance();

        if (insuranceRepository.existsByPolicyNumber(insuranceDto.getPolicyNumber())) {
            throw new DuplicateInsurancePolicyException(
                    "Insurance policy number already exists: " + insuranceDto.getPolicyNumber()
            );
        }

        Patient patient = patientMapper.toEntity(request);
        Patient saved = patientRepository.save(patient);
        return patientMapper.toDto(saved);
    }

}
