package com.lopes.clinic.util;

import com.lopes.clinic.model.Patient;

import java.util.ArrayList;
import java.util.List;


/**
 * The type Clinic utils.
 */
public class ClinicUtils {

    /**
     * The constant patientIdCounter.
     */
    private static Long patientIdCounter = 1L;

    /**
     * Creates patient list list.
     *
     * @return the list
     */
    public static List<Patient> createsPatientList() {
        Patient p1 = new Patient(generatedPatientId(), "Anderson", "Lopes", 37);
        Patient p2 = new Patient(generatedPatientId(), "Helena", "Lopes", 11);
        Patient p3 = new Patient(generatedPatientId(), "Elaine", "Lopes", 32);

        List<Patient> list = new ArrayList<Patient>();
        list.add(p1);
        list.add(p2);
        list.add(p3);

        return list;
    }

    /**
     * Generated patient id long.
     *
     * @return the long
     */
    public static Long generatedPatientId() {
        return patientIdCounter++;
    }

}
