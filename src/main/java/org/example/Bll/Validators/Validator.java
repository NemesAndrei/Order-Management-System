package org.example.Bll.Validators;

/**
 * @author Nemes Mihnea-Andrei
 * Validator
 * @since 27 Apr, 2021
 */
public interface Validator<T> {

    /**
     * validate
     *
     * @param t
     * @return boolean
     */
    public boolean validate(T t);
}
