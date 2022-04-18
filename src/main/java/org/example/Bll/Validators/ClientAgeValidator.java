package org.example.Bll.Validators;


import org.example.Model.Client;

import javax.swing.*;

/**
 * @author Nemes Mihnea-Andrei
 * Client's age validator
 * @since 27 Apr, 2021
 */
public class ClientAgeValidator implements Validator<Client> {

    private static final int MIN_AGE = 7;
    private static final int MAX_AGE = 30;

    /**
     * it checks whether the supplied age is between 7 and 30 years old, if so returns true, else returns false
     *
     * @param t
     * @return boolean
     */
    public boolean validate(Client t) {

        if (t.getAge() < MIN_AGE || t.getAge() > MAX_AGE) {
            JOptionPane.showMessageDialog(null, "Age must be between 7 and 30", "Error message", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }
}
