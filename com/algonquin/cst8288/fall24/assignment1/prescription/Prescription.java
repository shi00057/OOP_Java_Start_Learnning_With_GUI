package com.algonquin.cst8288.fall24.assignment1.prescription;

import com.algonquin.cst8288.fall24.assignment1.patient.Patient;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles the generation of the full prescription report, consolidating different
 * treatment plans and including patient details.
 * 
 * Author: Guokai Shi
 */
public class Prescription {

    private Patient patient;  // Store the entire patient object
    private List<String> treatmentPlans; // To hold all the treatment details

    /**
     * Constructs a Prescription for the specified patient.
     * 
     * @param patient The patient for whom the prescription is generated.
     */
    public Prescription(Patient patient) {
        this.patient = patient;
        this.treatmentPlans = new ArrayList<>();
    }

    /**
     * Adds a treatment plan to the prescription.
     * 
     * @param treatmentPlan The treatment plan to be added.
     */
    public void addTreatmentPlan(String treatmentPlan) {
        treatmentPlans.add(treatmentPlan);
    }

    /**
     * Generates the full prescription as a consolidated report, including patient details.
     * 
     * @return A string containing the full prescription details along with patient information.
     */
    public String generateFullPrescription() {
        StringBuilder prescription = new StringBuilder();

        // Append patient details
        prescription.append("Prescription for ").append(patient.getName()).append("\n")
                    .append("=========================================\n")
                    .append("Patient ID: ").append(patient.getId()).append("\n")
                    .append("Phone: ").append(patient.getPhoneNumber()).append("\n")
                    .append("Email: ").append(patient.getEmail()).append("\n")
                    .append("Date of Birth: ").append(patient.getDateOfBirth()).append("\n")
                    .append("Age: ").append(patient.getAge()).append("\n")
                    .append("-----------------------------------------\n");

        // Append all treatment plans
        for (String plan : treatmentPlans) {
            prescription.append(plan).append("\n");
        }

        return prescription.toString();
    }
}
