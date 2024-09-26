package com.algonquin.cst8288.fall24.assignment1.management;

import com.algonquin.cst8288.fall24.assignment1.patient.Inpatient;
import com.algonquin.cst8288.fall24.assignment1.patient.Outpatient;
import com.algonquin.cst8288.fall24.assignment1.patient.Patient;
import com.algonquin.cst8288.fall24.assignment1.prescription.Prescription;
import com.algonquin.cst8288.fall24.assignment1.treatment.MedicationTreatmentPlan;
import com.algonquin.cst8288.fall24.assignment1.treatment.SurgeryTreatmentPlan;
import com.algonquin.cst8288.fall24.assignment1.treatment.TreatmentPlan;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;
import java.io.File;

/**
 * Handles the processing of patient information, including creating patients,
 * validating their data, generating treatment plans, and generating printable prescriptions.
 * 
 * Author: Guokai Shi
 */
public class PatientManagement {

    // Singleton instance
    private static PatientManagement instance;

    // Path for saving prescription files (default)
    private String prescriptionPath = "./prescription_Demo/";

    // Private constructor to prevent instantiation
    private PatientManagement() {}

    // Method to get the Singleton instance
    public static PatientManagement getInstance() {
        if (instance == null) {
            instance = new PatientManagement();
        }
        return instance;
    }

    /**
     * Validates and creates an outpatient, then processes the treatment plan.
     * 
     * @param id The patient's ID.
     * @param name The patient's name.
     * @param email The patient's email address.
     * @param phoneNumber The patient's phone number.
     * @param dateOfBirth The patient's date of birth.
     * @param appointmentDate The outpatient's appointment date.
     * @param condition The patient's medical condition.
     * @throws IOException If any error occurs during file operations.
     */
    public void processOutpatient(String id, String name, String email, String phoneNumber, String dateOfBirth, String appointmentDate, String condition) throws IOException {
        // Validate patient data
        validatePatientData(name, email, phoneNumber, dateOfBirth);
        
        // Create outpatient
        Patient outpatient = new Outpatient(id, name, email, phoneNumber, dateOfBirth, appointmentDate);
        outpatient.setPlannedTreatment(condition);
        
        // Process treatment and prescription
        TreatmentPlan medicationPlan = new MedicationTreatmentPlan();
        processPatient(outpatient, medicationPlan);
    }

    /**
     * Validates and creates an inpatient, then processes the treatment plan.
     * 
     * @param id The patient's ID.
     * @param name The patient's name.
     * @param email The patient's email address.
     * @param phoneNumber The patient's phone number.
     * @param dateOfBirth The patient's date of birth.
     * @param roomNumber The inpatient's room number.
     * @param condition The patient's medical condition.
     * @throws IOException If any error occurs during file operations.
     */
    public void processInpatient(String id, String name, String email, String phoneNumber, String dateOfBirth, String roomNumber, String condition) throws IOException {
        // Validate patient data
        validatePatientData(name, email, phoneNumber, dateOfBirth);
        
        // Create inpatient
        Patient inpatient = new Inpatient(id, name, email, phoneNumber, dateOfBirth, roomNumber);
        inpatient.setPlannedTreatment(condition);
        
        // Process treatment and prescription
        TreatmentPlan treatmentPlan;
        if ("Acute".equals(condition)) {
            treatmentPlan = new SurgeryTreatmentPlan();
        } else {
            treatmentPlan = new MedicationTreatmentPlan();
        }
        processPatient(inpatient, treatmentPlan);
    }

    /**
     * Validates patient data such as name, email, phone number, and date of birth.
     * 
     * @param name The patient's name.
     * @param email The patient's email address.
     * @param phoneNumber The patient's phone number.
     * @param dateOfBirth The patient's date of birth.
     * @throws IllegalArgumentException if any validation fails.
     */
    private void validatePatientData(String name, String email, String phoneNumber, String dateOfBirth) {
        if (!DataValidator.isValidName(name)) {
            throw new IllegalArgumentException("Invalid name: " + name);
        }
        if (!DataValidator.isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email: " + email);
        }
        if (!DataValidator.isValidPhoneNumber(phoneNumber)) {
            throw new IllegalArgumentException("Invalid phone number: " + phoneNumber);
        }
        if (!DataValidator.isValidDateOfBirth(dateOfBirth)) {
            throw new IllegalArgumentException("Invalid date of birth: " + dateOfBirth);
        }
    }

    /**
     * Processes the patient data, generates treatment plans, and creates prescription reports.
     *
     * @param patient The patient whose treatment plan is being processed.
     * @param treatmentPlan The treatment plan to be applied.
     * @throws IOException If any error occurs during file operations.
     */
    public void processPatient(Patient patient, TreatmentPlan treatmentPlan) throws IOException {
        // Calculate and set patient's age
        long age = calculateAge(patient.getDateOfBirth());
        patient.setAge(age);

        // Delegate treatment plan creation
        String treatmentDetails = treatmentPlan.createTreatmentPlan(patient);

        // Create the prescription object
        Prescription prescription = new Prescription(patient);
        prescription.addTreatmentPlan(treatmentDetails);
        patient.setPrescription(prescription);

        // Generate prescription report
        generatePrescription(patient);
    }

    /**
     * Calculates the patient's age based on their date of birth.
     * 
     * @param dateOfBirth The patient's date of birth.
     * @return The calculated age in years.
     */
    private long calculateAge(String dateOfBirth) {
        if (dateOfBirth == null || dateOfBirth.isEmpty()) {
            return 0;
        }

        try {
            LocalDate birthDate = LocalDate.parse(dateOfBirth);  
            LocalDate currentDate = LocalDate.now();
            return Period.between(birthDate, currentDate).getYears();
        } catch (DateTimeParseException e) {
            System.err.println("Invalid date format for patient: " + dateOfBirth);
            return 0;
        }
    }

    /**
     * Generates prescription reports in both plain text and HTML formats.
     * 
     * @param patient The patient for whom the prescription is generated.
     * @throws IOException If any error occurs during file operations.
     */
    private void generatePrescription(Patient patient) throws IOException {
        // Ensure the patient has a valid prescription
        if (patient.getPrescription() == null) {
            throw new IllegalArgumentException("The patient " + patient.getName() + " does not have a prescription.");
        }

        // Fetch the full prescription details
        String fullPrescription = patient.getPrescription().generateFullPrescription();

        // Save the prescription in both .txt and .html formats
        saveToFile(fullPrescription, patient.getName() + "_prescription.txt");
        saveToHTMLFile(fullPrescription, patient.getName() + "_prescription.html");
    }

    private void saveToFile(String report, String fileName) throws IOException {
        ensureDirectoryExists(); // Ensure the directory exists before saving
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(prescriptionPath + fileName))) {
            writer.write(report);
        }
    }

    private void saveToHTMLFile(String report, String fileName) throws IOException {
        ensureDirectoryExists(); // Ensure the directory exists before saving
        String htmlReport = "<html><body><pre>" + report + "</pre></body></html>";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(prescriptionPath + fileName))) {
            writer.write(htmlReport);
        }
    }

    /**
     * Ensures that the directory for saving files exists, creating it if necessary.
     */
    private void ensureDirectoryExists() {
        File directory = new File(prescriptionPath);
        if (!directory.exists()) {
            directory.mkdirs();  // Create the directory if it doesn't exist
        }
    }
}
