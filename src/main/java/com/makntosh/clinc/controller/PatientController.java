package com.makntosh.clinc.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.makntosh.clinc.model.MedicalRecord;
import com.makntosh.clinc.model.Patient;
import com.makntosh.clinc.model.PatientAllergy;
import com.makntosh.clinc.repository.MedicalRecordRepository;
import com.makntosh.clinc.repository.PatientAllergyRepository;
import com.makntosh.clinc.repository.PatientRepository;
/**
 * 
 * @author Admin
 * @version 1.0
 * @since 2021
 *
 */
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class PatientController {

	@Autowired
	PatientRepository repo;
	
	@Autowired
	MedicalRecordRepository medicalRecordRepo;
	
	@Autowired
	PatientAllergyRepository allergyRepo;
	
	/**
	 * 
	 * @return Return List of Patient object
	 */
	@GetMapping("/getPatient")
	public List<Patient> getAllPatients(){
		return repo.findAll();
	}
	
	/**
	 * 
	 * @param patient - Patient object is passed to the method
	 */
	@PostMapping("/addPatient")
	public void addPatient(@RequestBody Patient patient) {
		repo.save(patient);
	}
	
	@DeleteMapping("/deletePatient/{id}")
	public void deletePatient(@PathVariable long id) {
		repo.deleteById(id);
	}
	/**
	 * 
	 * @return
	 */
	@GetMapping("/totalPatient")
	public int totalPatient() {
		return repo.getTotalPatients(); 
	}
	/**
	 * 
	 * @return Total number of patients are returned
	 */
	@GetMapping("/totalPatientToday")
	public int totalPatientToday() {
		return repo.getTotalPatientsToday(); 
	}
	
	/**
	 * Get patient by ID with full details
	 * @param patientId - Patient ID
	 * @return Patient details or 404 if not found
	 */
	@GetMapping("/patient/{patientId}")
	public ResponseEntity<Patient> getPatientById(@PathVariable long patientId) {
		Optional<Patient> patient = repo.findById(patientId);
		if (patient.isPresent()) {
			return ResponseEntity.ok(patient.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	/**
	 * Get complete medical history for a patient
	 * @param patientId - Patient ID
	 * @return List of medical records
	 */
	@GetMapping("/patient/{patientId}/medical-history")
	public List<MedicalRecord> getPatientMedicalHistory(@PathVariable long patientId) {
		return medicalRecordRepo.findByPatientId(patientId);
	}
	
	/**
	 * Get patient allergies
	 * @param patientId - Patient ID
	 * @return List of active allergies
	 */
	@GetMapping("/patient/{patientId}/allergies")
	public List<PatientAllergy> getPatientAllergies(@PathVariable long patientId) {
		return allergyRepo.findActiveAllergiesByPatientId(patientId);
	}
}
