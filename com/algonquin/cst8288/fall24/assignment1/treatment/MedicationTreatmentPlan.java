package com.algonquin.cst8288.fall24.assignment1.treatment;

import com.algonquin.cst8288.fall24.assignment1.patient.Patient;

/**
 * Implements the medication treatment plan by calculating dosage, duration, and medication
 * based on the patient's age and medical condition.
 * 
 * <p>
 * Author: Guokai Shi
 * </p>
 */
public class MedicationTreatmentPlan implements TreatmentPlan {

    /**
     * Generates a treatment plan for the patient by using the patient's details such as age
     * and medical condition.
     * 
     * @param patient The patient for whom the treatment plan is generated.
     * @return A string representing the detailed treatment plan.
     */
    @Override
public String createTreatmentPlan(Patient patient) {
    long age = patient.getAge(); // Age now fetched directly from the patient
    StringBuilder plan = new StringBuilder();
    String dosage;
    String medication;
    long duration;

    // Determine dosage based on the patient's age
    if (age < 6) {
        dosage = "1 time/day (CHILD)";
    } else if (age <= 18) {
        dosage = "2 times/day (YOUTH)";
    } else {
        dosage = "3 times/day (ADULT)";
    }

    // Get the planned treatment from the patient
    String conditionType = patient.getPlannedTreatment(); // Assuming condition is now part of the patient's planned treatment

    // Check if plannedTreatment is null
    if (conditionType == null) {
        throw new IllegalArgumentException("Planned treatment is not set for the patient: " + patient.getName());
    }

    // Determine treatment duration and medication type based on condition
    switch (conditionType) {
        case "Acute":
            duration = 7;
            medication = "No Medication";
            break;
        case "Infection":
            duration = 14;
            medication = "Antibiotics";
            break;
        case "Chronic":
            duration = 180;
            medication = "Specialized Medication";
            break;
        default:
            throw new IllegalArgumentException("Unknown condition type: " + conditionType);
    }

    // Build the treatment plan details
    plan.append("Condition: ").append(conditionType).append("\n")
        .append("Medication: ").append(medication).append("\n")
        .append("Dosage: ").append(dosage).append("\n")
        .append("Duration: ").append(duration).append(" days\n");

    return plan.toString();
}

}
