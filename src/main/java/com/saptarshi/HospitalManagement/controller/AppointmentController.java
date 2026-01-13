package com.saptarshi.HospitalManagement.controller;

import com.saptarshi.HospitalManagement.dto.AppointmentResponseDto;
import com.saptarshi.HospitalManagement.service.AppointmentService;
import com.saptarshi.HospitalManagement.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/appointment")
@RequiredArgsConstructor
public class AppointmentController {
    private final AppointmentService appointmentService;
    private final PatientService patientService;

    @GetMapping
    public List<AppointmentResponseDto> getAppointments() {
        return appointmentService.getAllAppointments();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentResponseDto> getAppointmentById(@PathVariable Long id) {
        return appointmentService.getAppointmentById(id);
    }

    @GetMapping("/date/{localDate}")
    public List<AppointmentResponseDto> getAppointmentByDate(@PathVariable LocalDate localDate){
        return appointmentService.getAppointmentByDate(localDate);
    }
}
