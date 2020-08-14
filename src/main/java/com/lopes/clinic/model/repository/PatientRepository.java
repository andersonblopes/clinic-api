package com.lopes.clinic.model.repository;

import com.lopes.clinic.model.Patient;
import com.lopes.clinic.util.ClinicUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * The type Patient repository.
 */
@Service
public class PatientRepository {

    /**
     * Instantiates a new Patient repository.
     */
    public PatientRepository() {
        getPatients();
    }

    /**
     * The Patients.
     */
    private List<Patient> patients;

    /**
     * Gets patients.
     *
     * @return the patients
     */
    public List<Patient> getPatients() {
        if (patients == null) {
            patients = ClinicUtils.createsPatientList();
        }
        return patients;
    }

    /**
     * Find by id optional.
     *
     * @param id the id
     * @return the optional
     */
    public Optional<Patient> findById(Long id) {
        return Optional.ofNullable(getPatients().stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null));
    }

    /**
     * Delete boolean.
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
     * Insert patient.
     *
     * @param patient the patient
     * @return the patient
     */
    public Patient insert(Patient patient) {
        patient.setId(ClinicUtils.generatedPatientId());
        getPatients().add(patient);
        return patient;
    }

    /**
     * Update patient.
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
