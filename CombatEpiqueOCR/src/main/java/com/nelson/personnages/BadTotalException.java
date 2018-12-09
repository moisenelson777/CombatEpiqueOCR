package com.nelson.personnages;

public class BadTotalException extends Exception {
    public BadTotalException() {
        super();
        System.err.println("Le total ne totalise pas le nombre escompté du niveau du joueur. Recommencez la saisie des points.");
    }
}
