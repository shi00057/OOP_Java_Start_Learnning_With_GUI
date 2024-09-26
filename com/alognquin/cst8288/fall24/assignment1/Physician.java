package com.alognquin.cst8288.fall24.assignment1;

import com.algonquin.cst8288.fall24.assignment1.management.PatientManagement;
import java.io.IOException;

/**
 * The Physician class now simply collects input parameters and delegates
 * patient processing to the PatientManagement class.
 * 
 * Author: Guokai Shi
 */
public class Physician {

    public static void main(String[] args) {
        // Create an instance of PatientManagement
        PatientManagement management = PatientManagement.getInstance();

        try {
            // Process Outpatient John Doe
            management.processOutpatient(
                "001", "John Doe", "johndoe@example.com", "123-456-7890", 
                "2015-10-17", "2024-10-05", "Infection"
            );

            // Process Inpatient Jane Smith
            management.processInpatient(
                "002", "Jane Smith", "janesmith@example.com", "098-765-4321", 
                "1987-07-21", "room 102", "Chronic"
            );

            // Process Inpatient Surgery Alice Johnson
            management.processInpatient(
                "003", "Alice Johnson", "alicej@example.com", "555-123-4567", 
                "1990-05-12", "room 101", "Acute"
            );

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
