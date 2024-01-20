//package com.example.patienthub;
//
//import com.example.patienthub.exception.PatientNotFoundException;
//import com.example.patienthub.model.Patient;
//import com.example.patienthub.repository.PatientRepository;
//import com.example.patienthub.service.PatientService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//class PatienthubApplicationTests {
//
//	@Autowired
//	private PatientService patientService;
//
//	@Autowired
//	private PatientRepository patientRepository;
//
//	@Test
//	public void testCreatePatient() {
//		Patient patientToCreate = new Patient();
//		patientToCreate.setName("John Doe");
//		patientToCreate.setAge(25);
//
//		Patient createdPatient = patientService.addPatient(patientToCreate);
//
//		assertNotNull(createdPatient.getId());
//		assertEquals("John Doe", createdPatient.getName()); // Corrected the expected name
//		assertEquals(25, createdPatient.getAge());
//	}
//
//	@Test
//	public void testGetPatientById() {
//		Patient patientToCreate = new Patient();
//		patientToCreate.setName("Raunak Jain");
//		patientToCreate.setAge(30);
//
//		Patient createdPatient = patientService.addPatient(patientToCreate);
//
//		Patient retrievedPatient = patientService.getPatientById(createdPatient.getId());
//
//		assertNotNull(retrievedPatient);
//		assertEquals(createdPatient.getId(), retrievedPatient.getId());
//		assertEquals("Raunak Jain", retrievedPatient.getName()); // Corrected the expected name
//		assertEquals(30, retrievedPatient.getAge());
//	}
//
//	@Test
//	public void testUpdatePatient() {
//		Patient patientToCreate = new Patient();
//		patientToCreate.setName("Sam Smith");
//		patientToCreate.setAge(40);
//
//		Patient createdPatient = patientService.addPatient(patientToCreate);
//
//		Patient updatedPatientData = new Patient();
//		updatedPatientData.setName("Updated Name");
//		updatedPatientData.setAge(45);
//
//		Patient updatedPatient = patientService.updatePatient(createdPatient.getId(), updatedPatientData);
//
//		assertNotNull(updatedPatient);
//		assertEquals(createdPatient.getId(), updatedPatient.getId());
//		assertEquals("Updated Name", updatedPatient.getName());
//		assertEquals(45, updatedPatient.getAge());
//	}
//
//	@Test
//	public void testDeletePatient() {
//		Patient patientToCreate = new Patient();
//		patientToCreate.setName("Delete Me");
//		patientToCreate.setAge(50);
//
//		Patient createdPatient = patientService.addPatient(patientToCreate);
//
//		patientService.deletePatient(createdPatient.getId());
//
//		assertThrows(PatientNotFoundException.class, () -> patientService.getPatientById(createdPatient.getId()));
//	}
//
//	@Test
//	public void testGetAllPatients() {
//		Patient patient1 = new Patient();
//		patient1.setName("Patient One");
//		patient1.setAge(55);
//
//		Patient patient2 = new Patient();
//		patient2.setName("Patient Two");
//		patient2.setAge(60);
//
//		patientService.addPatient(patient1);
//		patientService.addPatient(patient2);
//
//		List<Patient> allPatients = patientService.getAllPatients();
//
//		assertEquals(2, allPatients.size());
//	}
//}
