package com.algonquin.cst8288.fall24.assignment1.patient;

/**
 * The Outpatient class represents a patient who visits the hospital but is not admitted.
 * It extends the abstract Patient class and adds specific attributes and behaviors
 * for outpatient treatment.
 * 
 * Author: Guokai Shi
 */
public class Outpatient extends Patient {
    private String appointmentDate;

    /**
     * Constructs an Outpatient with the specified details.
     * 
     * @param id The patient's ID.
     * @param name The patient's name.
     * @param email The patient's email.
     * @param phoneNumber The patient's phone number.
     * @param dateOfBirth The patient's date of birth.
     * @param appointmentDate The date of the patient's appointment.
     */
    public Outpatient(String id, String name, String email, String phoneNumber, String dateOfBirth, String appointmentDate) {
        super(id, name, email, phoneNumber, dateOfBirth);
        this.appointmentDate = appointmentDate;
    }

    /**
     * Gets the date of the patient's appointment.
     * 
     * @return The appointment date of the outpatient.
     */
    public String getAppointmentDate() {
        return appointmentDate;
    }

    /**
     * Sets the date of the patient's appointment.
     * 
     * @param appointmentDate The new appointment date of the outpatient.
     */
    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    /**
     * Since outpatients are not admitted, this method throws an UnsupportedOperationException.
     */
    @Override
    public void admit() {
        throw new UnsupportedOperationException("Outpatients are not admitted to the hospital.");
    }
}
