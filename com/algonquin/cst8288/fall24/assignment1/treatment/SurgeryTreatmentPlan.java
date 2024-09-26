package com.algonquin.cst8288.fall24.assignment1.treatment;

import com.algonquin.cst8288.fall24.assignment1.patient.Patient;

/**
 * 
 * This class is responsible for implementing the business logic related to generating a treatment plan. 
 * 
 * You can use any method you prefer, such as StringBuilder or standard String, to construct the plan. Think of
 * treatment plan is a doctor notes in a summary format. 
 * Later, you will attach the treatment plan to detailed prescription.
 * 
 * An example might look like this:
 * "The patient is diagnosed with xxx and requires surgery.
 *  The patient is xxx years old with a risk factor of xxx. It is required to perform a minor surgery
 *  in order to address the xxx issue."
 * 
 * */






/**
 * SurgeryTreatmentPlan generates a treatment plan for surgery.
 * This class implements the TreatmentPlan interface.
 */
public class SurgeryTreatmentPlan implements TreatmentPlan {

    /**
     * Generates a surgery treatment plan for the given patient.
     *
     * @param patient The patient object containing personal details.
     * @return A string representing the surgery treatment plan in a document-like format.
     */
    @Override
    public String createTreatmentPlan(Patient patient) {
        StringBuilder plan = new StringBuilder();

        // Start with a general description
        plan.append("Diagnosis: The patient is diagnosed with a condition that requires surgery.\n");
        plan.append("The patient is ").append(patient.getAge()).append(" years old.\n");

        // Determine risk factor and fasting requirement based on age
        if (patient.getAge() < 6) {
            plan.append("Risk Factor: High\n");
            plan.append("Fasting: No Fasting Required\n");
            plan.append("Follow-Up: Next Day\n");
        } else if (patient.getAge() >= 6 && patient.getAge() <= 18) {
            plan.append("Risk Factor: Medium\n");
            plan.append("Fasting: 2 Hours Before Surgery\n");
            plan.append("Follow-Up: In a Week\n");
        } else {
            plan.append("Risk Factor: Low\n");
            plan.append("Fasting: 8 Hours Before Surgery\n");
            plan.append("Follow-Up: In a Month\n");
        }

        // Example: Adding the surgery date
        plan.append("Surgery Date: To be scheduled by the surgeon\n");

        // Finish with a summary
        plan.append("\nSummary:\n");
        plan.append("The surgery requires ")
            .append((patient.getAge() < 6) ? "no fasting" : (patient.getAge() <= 18) ? "2 hours fasting before the surgery" : "8 hours fasting before the surgery")
            .append(".\n");
        plan.append("The patient will need a follow-up ")
            .append((patient.getAge() < 6) ? "the next day" : (patient.getAge() <= 18) ? "in a week" : "in a month")
            .append(".\n");

        return plan.toString();
    }
}