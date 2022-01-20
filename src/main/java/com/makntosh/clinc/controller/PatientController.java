package com.makntosh.clinc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.makntosh.clinc.model.Patient;
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
}
