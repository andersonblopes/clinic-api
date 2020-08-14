package com.lopes.clinic.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.lopes.clinic.model.Patient;

public class ClinicUtills {

	private static Long patientIdCounter = 1L;

	public static List<Patient> cretesPatientList() {
		Patient p1 = new Patient(generatedPatientId(), "Anderson", "Lopes", 37);
		Patient p2 = new Patient(generatedPatientId(), "Helena", "Lopes", 11);
		Patient p3 = new Patient(generatedPatientId(), "Elaine", "Lopes", 32);

		List<Patient> list = new ArrayList<Patient>();
		list.add(p1);
		list.add(p2);
		list.add(p3);

		return list;
	}

	public static Long generatedPatientId() {
		return patientIdCounter++;
	}

}
