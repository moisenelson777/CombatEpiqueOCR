package com.nelson.personnages;

public class BadNumberException extends Exception {
    public BadNumberException() {
        super();
        System.err.println("La valeur saisie d�passe la limite autoris�e.");
    }
}
