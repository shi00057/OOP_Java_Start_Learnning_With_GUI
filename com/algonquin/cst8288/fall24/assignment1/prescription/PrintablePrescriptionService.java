package com.algonquin.cst8288.fall24.assignment1.prescription;

import com.algonquin.cst8288.fall24.assignment1.patient.Patient;

/**
 * This class is responsible for generating printable prescriptions
 * for patients in a plain text format. It implements the PrescriptionService interface.
 * 
 * <p>
 * Author: Guokai Shi
 * </p>
 */
public class PrintablePrescriptionService implements PrescriptionService {

    /**
     * Generates a prescription for the given patient in plain text format.
     * 
     * @param patient The patient for whom the prescription is generated.
     * @return The prescription details in plain text format.
     */
    @Override
    public String generatePrescription(Patient patient) {
        validatePatient(patient);

        // Fetch the full prescription details from the Prescription object
        String fullPrescription = patient.getPrescription().generateFullPrescription();

        // Format it as plain text
        return formatPrescription(fullPrescription);
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

    /**
     * Formats the prescription in a plain text format.
     * 
     * @param fullPrescription The complete prescription details.
     * @return The formatted prescription in plain text.
     */
    private String formatPrescription(String fullPrescription) {
        return new StringBuilder()
                .append("----- Prescription -----\n")
                .append(fullPrescription)
                .append("\n------------------------\n")
                .toString();
    }
}
