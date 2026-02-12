package com.saptarshi.HospitalManagement.service;

import com.saptarshi.HospitalManagement.dto.AdmitPatientInsuranceDto;
import com.saptarshi.HospitalManagement.dto.AdmitPatientRequest;
import com.saptarshi.HospitalManagement.entities.Insurance;
import com.saptarshi.HospitalManagement.exceptions.DuplicateInsurancePolicyException;
import com.saptarshi.HospitalManagement.repository.InsuranceRepository;
import com.saptarshi.HospitalManagement.repository.PatientRepository;
import com.saptarshi.HospitalManagement.type.BloodGroupType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@ActiveProfiles("test")
class PatientServiceTest {

    @Autowired
    private PatientService patientService;

    @Autowired
    private InsuranceRepository insuranceRepository;

    @Autowired
    private PatientRepository patientRepository;

    @BeforeEach
    void setUp() {
        patientRepository.deleteAll();
        insuranceRepository.deleteAll();
    }

    @Test
    void admitPatient_savesPatientAndInsurance() {
        AdmitPatientRequest request = new AdmitPatientRequest(
                "Jane Doe",
                LocalDate.of(1990, 1, 1),
                "jane.doe@example.com",
                "F",
                BloodGroupType.O_POSITIVE,
                new AdmitPatientInsuranceDto(
                        "Test Provider",
                        "POL-XYZ-1",
                        LocalDate.now().plusYears(1)
                )
        );

        var saved = patientService.admitPatient(request);

        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getInsurance_id()).isNotNull();
        Insurance insurance = insuranceRepository.findById(saved.getInsurance_id()).orElseThrow();
        assertThat(insurance.getPolicyNumber()).isEqualTo("POL-XYZ-1");
    }

    @Test
    void admitPatient_throwsOnDuplicatePolicyNumber() {
        insuranceRepository.save(buildInsurance("POL-DUP-1"));

        AdmitPatientRequest request = new AdmitPatientRequest(
                "John Roe",
                LocalDate.of(1985, 5, 20),
                "john.roe@example.com",
                "M",
                BloodGroupType.A_POSITIVE,
                new AdmitPatientInsuranceDto(
                        "Test Provider",
                        "POL-DUP-1",
                        LocalDate.now().plusYears(1)
                )
        );

        assertThatThrownBy(() -> patientService.admitPatient(request))
                .isInstanceOf(DuplicateInsurancePolicyException.class);
    }

    private Insurance buildInsurance(String policyNumber) {
        Insurance insurance = new Insurance();
        insurance.setProvider("Test Provider");
        insurance.setPolicyNumber(policyNumber);
        insurance.setValidUntil(LocalDate.now().plusYears(2));
        return insurance;
    }
}
