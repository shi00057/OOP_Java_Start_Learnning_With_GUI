package com.algonquin.cst8288.fall24.assignment1.treatment;

import com.algonquin.cst8288.fall24.assignment1.patient.Patient;

/**
 * The TreatmentPlan interface defines the method for generating treatment plans
 * based on the patient's medical details. Different implementations of this
 * interface will provide specific types of treatment plans.
 * 
 * Author: Guokai Shi
 */
public interface TreatmentPlan {

    /**
     * Creates a treatment plan for the given patient.
     * 
     * @param patient The patient for whom the treatment plan is being created.
     * @return A string containing the details of the treatment plan.
     */
    String createTreatmentPlan(Patient patient);
}
