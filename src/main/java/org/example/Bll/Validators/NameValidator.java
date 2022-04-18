package org.example.Bll.Validators;


import org.example.Model.Client;

import javax.swing.*;

/**
 * @author Nemes Mihnea-Andrei
 * Client's name validator
 * @since 27 Apr, 2021
 */
public class NameValidator implements Validator<Client> {

    /**
     * it checks whether the supplied name complies with the given pattern, if so returns true, else it returns false
     *
     * @param t
     * @return boolean
     */
    public boolean validate(Client t) {
        if (!t.getName().matches("[a-zA-Z]+")) {
            JOptionPane.showMessageDialog(null, "Wrong name pattern", "Error message", JOptionPane.WARNING_MESSAGE);
            return false;
            //throw new IllegalArgumentException("Name must be comprised of only letters!");
        }
        return true;
    }
}