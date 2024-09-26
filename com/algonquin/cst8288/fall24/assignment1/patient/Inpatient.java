package com.algonquin.cst8288.fall24.assignment1.patient;

/**
 * The Inpatient class represents a patient who is admitted to the hospital.
 * It extends the abstract Patient class and adds specific attributes and behaviors
 * for inpatient treatment.
 * 
 * Author: Guokai Shi
 */
public class Inpatient extends Patient {
    private String roomNumber;

    /**
     * Constructs an Inpatient with the specified details.
     * 
     * @param id The patient's ID.
     * @param name The patient's name.
     * @param email The patient's email.
     * @param phoneNumber The patient's phone number.
     * @param dateOfBirth The patient's date of birth.
     * @param roomNumber The room number where the patient is admitted.
     */
    public Inpatient(String id, String name, String email, String phoneNumber, String dateOfBirth, String roomNumber) {
        super(id, name, email, phoneNumber, dateOfBirth);
        this.roomNumber = roomNumber;
    }

    /**
     * Gets the room number where the patient is admitted.
     * 
     * @return The room number of the inpatient.
     */
    public String getRoomNumber() {
        return roomNumber;
    }

    /**
     * Sets the room number for the patient.
     * 
     * @param roomNumber The new room number of the inpatient.
     */
    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    /**
     * Admits the patient to the hospital by assigning them to a room.
     */
    @Override
    public void admit() {
        System.out.println(getName() + " has been admitted to room " + getRoomNumber() + ".");
    }
}
