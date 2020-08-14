package com.lopes.clinic.controller;

import com.lopes.clinic.model.Patient;
import com.lopes.clinic.model.repository.PatientRepository;
import com.lopes.clinic.util.DateUtil;
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

import java.util.List;
import java.util.Optional;


/**
 * The type Patient controller.
 */
@RestController
@RequestMapping("/patient")
public class PatientController {


    /**
     * The Patient repository.
     */
    private PatientRepository patientRepository;

    /**
     * The Date util.
     */
    private DateUtil dateUtil;

    /**
     * Instantiates a new Patient controller.
     *
     * @param patientRepository the patient repository
     * @param dateUtil          the date util
     */
    public PatientController(PatientRepository patientRepository, DateUtil dateUtil) {
        this.patientRepository = patientRepository;
        this.dateUtil = dateUtil;
    }
    
    /**
     * Find all response entity.
     *
     * @return the response entity
     */
    @GetMapping
    public ResponseEntity<List<Patient>> findAll() {
        return new ResponseEntity<List<Patient>>(patientRepository.getPatients(), HttpStatus.OK);
    }

    /**
     * Find by id response entity.
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
     * Create patient response entity.
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
     * Update patient response entity.
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
     * Remove patient response entity.
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
