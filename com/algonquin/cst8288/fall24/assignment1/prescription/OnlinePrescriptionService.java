package com.algonquin.cst8288.fall24.assignment1.prescription;

import com.algonquin.cst8288.fall24.assignment1.patient.Patient;

/**
 * This class is responsible for generating online prescriptions
 * for patients in an HTML format. It implements the PrescriptionService interface.
 * 
 * <p>
 * Author: Guokai Shi
 * </p>
 */
public class OnlinePrescriptionService implements PrescriptionService {

    private static final String HTML_HEADER = "<html><body><h1>Online Prescription</h1><pre>";
    private static final String HTML_FOOTER = "</pre></body></html>";

    /**
     * Generates a prescription for the given patient in HTML format.
     * 
     * @param patient The patient for whom the prescription is generated.
     * @return The prescription details in HTML format.
     */
    @Override
    public String generatePrescription(Patient patient) {
        validatePatient(patient);

        // Fetch the full prescription details
        Prescription prescription = patient.getPrescription();
        String fullPrescription = prescription.generateFullPrescription();

        // Format the prescription as HTML
        return HTML_HEADER + fullPrescription + HTML_FOOTER;
    }

    /**
     * Validates the patient and its prescription.
     * 
     * @param patient The patient to validate.
     */
    private void validatePatient(Patient patient) {
        if (patient == null) {
            throw new IllegalArgumentException("Patient cannot be null.");
        }

        if (patient.getPrescription() == null) {
            throw new IllegalArgumentException("The patient " + patient.getName() + " does not have a valid prescription.");
        }

        // Ensure the age is set by calling getAge()
        long age = patient.getAge();
        if (age == 0) {
            throw new IllegalArgumentException("The patient's age is invalid.");
        }
    }
}
