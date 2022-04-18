package org.example.Bll.Validators;


import org.example.Model.Product;

import javax.swing.*;

/**
 * @author Nemes Mihnea-Andrei
 * Product's price validator
 * @since 27 Apr, 2021
 */
public class PriceValidator implements Validator<Product> {

    /**
     * it checks whether the supplied price is greater than 0, if so returns true, else returns false
     *
     * @param t
     * @return boolean
     */
    public boolean validate(Product t) {

        if (t.getPrice() < 0) {
            JOptionPane.showMessageDialog(null, "Price cannot be smaller than 0", "Error message", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }
}