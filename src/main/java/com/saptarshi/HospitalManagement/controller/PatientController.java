package com.saptarshi.HospitalManagement.controller;


import com.saptarshi.HospitalManagement.dto.AdmitPatientRequest;
import com.saptarshi.HospitalManagement.dto.PatientAppointmentDto;
import com.saptarshi.HospitalManagement.dto.PatientDto;
import com.saptarshi.HospitalManagement.dto.PatientUpdateRequest;
import com.saptarshi.HospitalManagement.service.PatientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;


@RestController
@RequestMapping("/patient")
@RequiredArgsConstructor
public class PatientController {
    private final PatientService patientService;

    @GetMapping
    public List<PatientDto> getPatients() {
        return patientService.getAllPatients();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDto> getPatientById(@PathVariable Long id) {
        return patientService.findPatientById(id);
    }

    @GetMapping("/appointment/{id}")
    public List<PatientAppointmentDto> getPatientAppointments(@PathVariable Long id) {
        return patientService.findPatientAppointments(id);
    }

    @PostMapping
    public ResponseEntity<?> admitPatient(@Valid @RequestBody AdmitPatientRequest request, UriComponentsBuilder uriBuilder) {
        var patient = patientService.admitPatient(request);
        if (patient == null)
            return new ResponseEntity<>("Conflict", HttpStatus.CONFLICT);
        var uri = uriBuilder.path("/patient/{id}").buildAndExpand(patient.getId()).toUri();
        return ResponseEntity.created(uri).body(patient);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientDto> update(@PathVariable Long id, @Valid @RequestBody PatientUpdateRequest request) {
        return patientService.updatePatient(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return patientService.deletePatient(id);
    }

}
