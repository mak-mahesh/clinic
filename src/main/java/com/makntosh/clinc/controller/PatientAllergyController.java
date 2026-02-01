package com.makntosh.clinc.controller;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.makntosh.clinc.model.PatientAllergy;
import com.makntosh.clinc.repository.PatientAllergyRepository;

/**
 * REST Controller for managing patient allergies
 * @author Admin
 * @version 1.0
 * @since 2021
 */
@RestController
@RequestMapping("/api/allergies")
@CrossOrigin(origins = "http://localhost:3000")
public class PatientAllergyController {

	@Autowired
	PatientAllergyRepository allergyRepo;

	/**
	 * Get all active allergies for a specific patient
	 * @param patientId - Patient ID
	 * @return List of active allergies
	 */
	@GetMapping("/patient/{patientId}")
	public List<PatientAllergy> getActiveAllergiesByPatientId(@PathVariable long patientId) {
		return allergyRepo.findActiveAllergiesByPatientId(patientId);
	}

	/**
	 * Get all allergies (active and inactive) for a specific patient
	 * @param patientId - Patient ID
	 * @return List of all allergies
	 */
	@GetMapping("/patient/{patientId}/all")
	public List<PatientAllergy> getAllAllergiesByPatientId(@PathVariable long patientId) {
		return allergyRepo.findAllAllergiesByPatientId(patientId);
	}

	/**
	 * Get allergies by severity for a patient
	 * @param patientId - Patient ID
	 * @param severity - Severity level (Mild, Moderate, Severe)
	 * @return List of allergies with specified severity
	 */
	@GetMapping("/patient/{patientId}/severity/{severity}")
	public List<PatientAllergy> getAllergiesBySeverity(@PathVariable long patientId, @PathVariable String severity) {
		return allergyRepo.findAllergiesBySeverity(patientId, severity);
	}

	/**
	 * Add a new allergy for a patient
	 * @param allergy - Patient allergy object
	 * @return Saved allergy record
	 */
	@PostMapping("/add")
	public PatientAllergy addAllergy(@RequestBody PatientAllergy allergy) {
		allergy.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		return allergyRepo.save(allergy);
	}

	/**
	 * Update an existing allergy
	 * @param allergyId - Allergy ID
	 * @param allergy - Updated allergy data
	 * @return Updated allergy or 404 if not found
	 */
	@PutMapping("/update/{allergyId}")
	public ResponseEntity<PatientAllergy> updateAllergy(@PathVariable long allergyId, 
			@RequestBody PatientAllergy allergy) {
		Optional<PatientAllergy> existingAllergy = allergyRepo.findById(allergyId);
		if (existingAllergy.isPresent()) {
			PatientAllergy existing = existingAllergy.get();
			existing.setAllergen(allergy.getAllergen());
			existing.setReaction(allergy.getReaction());
			existing.setSeverity(allergy.getSeverity());
			existing.setNotes(allergy.getNotes());
			existing.setActive(allergy.isActive());
			
			return ResponseEntity.ok(allergyRepo.save(existing));
		}
		return ResponseEntity.notFound().build();
	}

	/**
	 * Deactivate an allergy (soft delete)
	 * @param allergyId - Allergy ID to deactivate
	 * @return Success response or 404 if not found
	 */
	@PutMapping("/deactivate/{allergyId}")
	public ResponseEntity<PatientAllergy> deactivateAllergy(@PathVariable long allergyId) {
		Optional<PatientAllergy> allergy = allergyRepo.findById(allergyId);
		if (allergy.isPresent()) {
			PatientAllergy existing = allergy.get();
			existing.setActive(false);
			return ResponseEntity.ok(allergyRepo.save(existing));
		}
		return ResponseEntity.notFound().build();
	}

	/**
	 * Delete an allergy permanently
	 * @param allergyId - Allergy ID to delete
	 * @return Success response or 404 if not found
	 */
	@DeleteMapping("/delete/{allergyId}")
	public ResponseEntity<Void> deleteAllergy(@PathVariable long allergyId) {
		if (allergyRepo.existsById(allergyId)) {
			allergyRepo.deleteById(allergyId);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}