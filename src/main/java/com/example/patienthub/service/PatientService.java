package com.example.patienthub.service;

import com.example.patienthub.exception.PatientNotFoundException;
import com.example.patienthub.model.Patient;
import com.example.patienthub.repository.PatientRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    private static final Logger logger = LogManager.getLogger(PatientService.class);

    @Autowired
    private PatientRepository patientRepository;

    @Cacheable("patients")
    public List<Patient> getAllPatients() {
        System.out.println("Going to H2 Database for getAllPatients");
        return patientRepository.findAll();
    }

    @Cacheable(value = "patients", key = "#id")
    public Patient getPatientById(Long id) {
        logger.info("Fetching patient with id: {}", id);
        Optional<Patient> optionalPatient = patientRepository.findById(id);

        if (optionalPatient.isPresent()) {
            Patient patient = optionalPatient.get();
            logger.info("Found patient: {}", patient);
            return patient;
        } else {
            logger.warn("Patient not found with id: {}", id);
            throw new PatientNotFoundException("Patient not found with id: " + id);
        }
    }

    @CacheEvict(value = "patients", allEntries = true)
    public Patient addPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @CacheEvict(value = "patients", key = "#id")
    public void deletePatient(Long id) {
        if (!patientRepository.existsById(id)) {
            throw new PatientNotFoundException("Patient not found with id: " + id);
        }
        patientRepository.deleteById(id);
    }

    @CachePut(value = "patients", key = "#id")
    public Patient updatePatient(Long id, Patient updatedPatient) {
        logger.info("Updating patient with id: {}", id);
        Optional<Patient> optionalPatient = patientRepository.findById(id);

        if (optionalPatient.isPresent()) {
            Patient existingPatient = optionalPatient.get();

            existingPatient.setName(updatedPatient.getName());
            existingPatient.setId(updatedPatient.getId());
            existingPatient.setAge(updatedPatient.getAge());
            existingPatient.setGender(updatedPatient.getGender());
            existingPatient.setContactNumber(updatedPatient.getContactNumber());

            return patientRepository.save(existingPatient);
        } else {
            logger.warn("Patient not found with id: {}", id);
            throw new PatientNotFoundException("Patient not found with id: " + id);
        }
    }
}