package com.lopes.clinic.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lopes.clinic.model.Patient;
import com.lopes.clinic.model.repository.PatientRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class PatientController.
 */
@RestController
@RequestMapping("/patient")
public class PatientController {

	/** The patient repository. */
	private PatientRepository patientRepository;

	/**
	 * Instantiates a new patient controller.
	 *
	 * @param patientRepository the patient repository
	 */
	public PatientController(PatientRepository patientRepository) {
		this.patientRepository = patientRepository;
	}

	/**
	 * Find all.
	 *
	 * @return the response entity
	 */
	@GetMapping
	public ResponseEntity<List<Patient>> findAll() {
		return new ResponseEntity<List<Patient>>(patientRepository.getPatients(), HttpStatus.OK);
	}

	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the response entity
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Patient> findById(@PathVariable Long id) {
		Optional<Patient> patientFound = patientRepository.findById(id);
		if (patientFound.isPresent()) {
			return new ResponseEntity<Patient>(patientFound.get(), HttpStatus.OK);
		}
		return new ResponseEntity<Patient>(HttpStatus.NOT_FOUND);
	}

	/**
	 * Creates the patient.
	 *
	 * @param patient the patient
	 * @return the response entity
	 */
	@PostMapping
	public ResponseEntity<Patient> createPatient(@RequestBody Patient patient) {
		if (patient == null || patient.getId() != null) {
			return ResponseEntity.badRequest().build();
		}
		return new ResponseEntity<Patient>(patientRepository.insert(patient), HttpStatus.CREATED);
	}

	/**
	 * Update patient.
	 *
	 * @param patient the patient
	 * @return the response entity
	 */
	@PutMapping
	public ResponseEntity<Patient> updatePatient(@RequestBody Patient patient) {

		if (patient == null || patient.getId() == null) {
			return ResponseEntity.badRequest().build();
		}

		Optional<Patient> patientFound = patientRepository.findById(patient.getId());
		if (patientFound.isPresent()) {
			return new ResponseEntity<Patient>(patientRepository.update(patient), HttpStatus.OK);
		} else {
			return ResponseEntity.notFound().build();
		}

	}

	/**
	 * Removes the patient.
	 *
	 * @param id the id
	 * @return the response entity
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Patient> removePatient(@PathVariable Long id) {
		return patientRepository.delete(id) ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
				: new ResponseEntity<Patient>(HttpStatus.NOT_FOUND);
	}

}
