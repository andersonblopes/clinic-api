package com.lopes.clinic.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.lopes.clinic.model.Patient;
import com.lopes.clinic.util.ClinicUtills;

// TODO: Auto-generated Javadoc
/**
 * The Class PatientRepository.
 */
@Service
public class PatientRepository {

	/**
	 * Instantiates a new patient repository.
	 */
	public PatientRepository() {
		getPatients();
	}

	/** The patients. */
	private List<Patient> patients;

	/**
	 * Gets the patients.
	 *
	 * @return the patients
	 */
	public List<Patient> getPatients() {
		if (patients == null) {
			patients = ClinicUtills.cretesPatientList();
		}
		return patients;
	}

	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the optional
	 */
	public Optional<Patient> findById(Long id) {
		return Optional.ofNullable(getPatients().stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null));
	}

	/**
	 * Delete.
	 *
	 * @param id the id
	 * @return the boolean
	 */
	public Boolean delete(Long id) {

		for (Patient p : getPatients()) {
			if (p.getId().equals(id)) {
				return getPatients().remove(p);
			}
		}
		return false;
	}

	/**
	 * Insert.
	 *
	 * @param patient the patient
	 * @return the patient
	 */
	public Patient insert(Patient patient) {
		patient.setId(ClinicUtills.generatedPatientId());
		getPatients().add(patient);
		return patient;
	}

	/**
	 * Update.
	 *
	 * @param patient the patient
	 * @return the patient
	 */
	public Patient update(Patient patient) {
		for (Patient p : getPatients()) {
			if (p.equals(patient)) {
				p.setFirstName(patient.getFirstName());
				p.setLastName(patient.getLastName());
				p.setAge(patient.getAge());
				patient = p;
			}
		}
		return patient;
	}

}
