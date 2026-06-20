package com.makntosh.clinc.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
/*Patient allergy info */
@Entity
@Table(name = "patient_allergy")
public class PatientAllergy {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long allergyId;
	
	@ManyToOne
	@JoinColumn(name = "patient_id", nullable = false)
	private Patient patient;
	
	@Column(name = "allergen", nullable = false)
	private String allergen;
	
	@Column(name = "reaction")
	private String reaction;
	
	@Column(name = "severity")
	private String severity; // Mild, Moderate, Severe
	
	@Column(name = "notes", length = 500)
	private String notes;
	
	@Column(name = "is_active")
	private boolean isActive = true;
	
	@Column(name = "created_at")
	private Timestamp createdAt;

	public PatientAllergy() {
		super();
	}

	public PatientAllergy(Patient patient, String allergen, String reaction, String severity, String notes) {
		super();
		this.patient = patient;
		this.allergen = allergen;
		this.reaction = reaction;
		this.severity = severity;
		this.notes = notes;
		this.isActive = true;
	}

	public long getAllergyId() {
		return allergyId;
	}

	public void setAllergyId(long allergyId) {
		this.allergyId = allergyId;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public String getAllergen() {
		return allergen;
	}

	public void setAllergen(String allergen) {
		this.allergen = allergen;
	}

	public String getReaction() {
		return reaction;
	}

	public void setReaction(String reaction) {
		this.reaction = reaction;
	}

	public String getSeverity() {
		return severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
}