package com.example.patienthub.controller;

import com.example.patienthub.exception.PatientNotFoundException;
import com.example.patienthub.model.Patient;
import com.example.patienthub.service.PatientService;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/patients")
@Api(tags = "Patient Controller", description = "Operations related to patients")
public class PatientController {

   private final Logger logger = LoggerFactory.getLogger(PatientController.class);

    @Autowired
    private PatientService patientService;

    @GetMapping
    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }

    @GetMapping("/{id}")
    @ApiOperation("Get a patient by ID")
    public Patient getPatientById(@PathVariable Long id) {
        try {
            return patientService.getPatientById(id);
        }catch (Exception e){
            throw new PatientNotFoundException(e.getMessage());
        }
    }

    @PostMapping
    @ApiOperation("Create a new patient")
    public Patient addPatient(@RequestBody Patient patient) {
        return patientService.addPatient(patient);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete a patient")
    public String deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return ("Patient deleted successfully");
    }

    @PutMapping("/{id}")
    @ApiOperation("Update a patient")
    public Object updatePatient(@PathVariable Long id, @RequestBody Patient updatedPatient) {
        try {
            Patient result = patientService.updatePatient(id, updatedPatient);
            logger.info("Patient with id {} updated successfully", id);
            return result;
        } catch (PatientNotFoundException e) {
            logger.warn("Error updating patient with id {}:", id);
            throw new PatientNotFoundException(e.getMessage());
        } catch (Exception e) {
            logger.error("Unexpected error updating patient with id {}", id, e);
            return "Internal Server Error";
        }
    }
}
