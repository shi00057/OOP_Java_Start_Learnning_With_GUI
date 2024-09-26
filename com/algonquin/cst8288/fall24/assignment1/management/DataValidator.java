package com.algonquin.cst8288.fall24.assignment1.management;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The DataValidator class provides static methods for validating patient data such as
 * name, email, phone number, and date of birth. It helps ensure that the data is well-formed and
 * adheres to the required format.
 * 
 * Author: Guokai Shi
 */
public class DataValidator {

    /**
     * Validates a patient's name. A valid name must be non-null and contain only
     * alphabetic characters and spaces.
     * 
     * @param name The name to validate.
     * @return true if the name is valid, false otherwise.
     */
    public static boolean isValidName(String name) {
        return name != null && name.matches("^[a-zA-Z\\s]+$");
    }

    /**
     * Validates a patient's email address. The email must follow a standard email
     * format.
     * 
     * @param email The email address to validate.
     * @return true if the email is valid, false otherwise.
     */
    public static boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email != null && email.matches(emailRegex);
    }

    /**
     * Validates a patient's phone number. The phone number must be a valid format
     * that can include optional country codes, spaces, dashes, or parentheses.
     * 
     * @param phoneNumber The phone number to validate.
     * @return true if the phone number is valid, false otherwise.
     */
    public static boolean isValidPhoneNumber(String phoneNumber) {
        String phoneRegex = "^(\\+\\d{1,2}\\s?)?\\(?\\d{3}\\)?[-.\\s]?\\d{3}[-.\\s]?\\d{4}$";
        return phoneNumber != null && phoneNumber.matches(phoneRegex);
    }

    /**
     * Validates the patient's date of birth. The date must be in the format YYYY-MM-DD and
     * should be a valid date.
     * 
     * @param dateOfBirth The date of birth to validate.
     * @return true if the date of birth is valid, false otherwise.
     */
public static boolean isValidDateOfBirth(String dateOfBirth) {
    if (dateOfBirth == null) {
        return false;  // Ensure it returns false when the input is null
    }
    try {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate.parse(dateOfBirth, formatter);  // This will throw an exception if the format is invalid
        return true;
    } catch (DateTimeParseException e) {
        return false;
    }
}

}

