package com.example.patienthub.Service;

import com.example.patienthub.exception.PatientNotFoundException;
import com.example.patienthub.model.Patient;
import com.example.patienthub.repository.PatientRepository;
import com.example.patienthub.service.PatientService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PatientServiceTest {

    @Mock
    private PatientRepository patientRepository;

    @InjectMocks
    private PatientService patientService;

    @Test
    public void testGetPatientById() {
        Long patientId = 1L;
        Patient mockPatient = new Patient();
        mockPatient.setId(patientId);
        mockPatient.setName("Raunak Jain");

        when(patientRepository.findById(patientId)).thenReturn(Optional.of(mockPatient));
        Patient retrievedPatient = patientService.getPatientById(patientId);
        assertNotNull(retrievedPatient);
        assertEquals(patientId, retrievedPatient.getId());
        assertEquals("Raunak Jain", retrievedPatient.getName());
    }

    @Test
    public void testGetPatientByIdNotFound() {

        Long nonExistentPatientId = 99L;
        when(patientRepository.findById(nonExistentPatientId)).thenReturn(Optional.empty());
        assertThrows(PatientNotFoundException.class, () -> patientService.getPatientById(nonExistentPatientId));
    }

}
