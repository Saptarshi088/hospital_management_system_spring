package com.saptarshi.HospitalManagement.service;

import com.saptarshi.HospitalManagement.dto.AdmitPatientInsuranceDto;
import com.saptarshi.HospitalManagement.dto.AdmitPatientRequest;
import com.saptarshi.HospitalManagement.dto.PatientAppointmentDto;
import com.saptarshi.HospitalManagement.dto.PatientDto;
import com.saptarshi.HospitalManagement.dto.PatientUpdateRequest;
import com.saptarshi.HospitalManagement.entities.Patient;
import com.saptarshi.HospitalManagement.exceptions.DuplicateInsurancePolicyException;
import com.saptarshi.HospitalManagement.mapper.AppointMapper;
import com.saptarshi.HospitalManagement.mapper.PatientMapper;
import com.saptarshi.HospitalManagement.repository.AppointmentRepository;
import com.saptarshi.HospitalManagement.repository.InsuranceRepository;
import com.saptarshi.HospitalManagement.repository.PatientRepository;
import jakarta.validation.Valid;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;
    private final AppointmentRepository appointmentRepository;
    private final AppointMapper appointMapper;
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
    public PatientDto admitPatient(@Valid AdmitPatientRequest request) {

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

    @Transactional
    public ResponseEntity<PatientDto> updatePatient(Long id, @Valid PatientUpdateRequest request) {
        var patientOpt = patientRepository.findById(id);
        if (patientOpt.isEmpty()) return ResponseEntity.notFound().build();

        var patient = patientOpt.get();
        var insurance = patient.getInsurance();

        var newPolicy = request.getInsurance().getPolicyNumber();
        if (!insurance.getPolicyNumber().equals(newPolicy) && insuranceRepository.existsByPolicyNumber(newPolicy)) {
            throw new DuplicateInsurancePolicyException("Insurance policy number already exists: " + newPolicy);
        }

        patient.setName(request.getName());
        patient.setBirthDate(request.getBirthDate());
        patient.setEmail(request.getEmail());
        patient.setGender(request.getGender());
        patient.setBloodGroup(request.getBloodGroup());

        insurance.setProvider(request.getInsurance().getProvider());
        insurance.setPolicyNumber(newPolicy);
        insurance.setValidUntil(request.getInsurance().getValidUntil());

        Patient saved = patientRepository.save(patient);
        return ResponseEntity.ok(patientMapper.toDto(saved));
    }

    @Transactional
    public ResponseEntity<Void> deletePatient(Long id) {
        var patientOpt = patientRepository.findById(id);
        if (patientOpt.isEmpty()) return ResponseEntity.notFound().build();

        appointmentRepository.deleteByPatientId(id);
        patientRepository.delete(patientOpt.get());
        return ResponseEntity.noContent().build();
    }

}
