package com.algonquin.cst8288.fall24.assignment1.prescription;

import com.algonquin.cst8288.fall24.assignment1.patient.Patient;

/**
 * The PrescriptionService interface defines the method for generating
 * prescriptions based on the patient's treatment plan and medical details.
 * 
 * Author: Guokai Shi
 */
public interface PrescriptionService {

    /**
     * Generates a detailed prescription for the given patient.
     * 
     * @param patient The patient for whom the prescription is being generated.
     * @return A string containing the formatted prescription details.
     */
    String generatePrescription(Patient patient);
}
