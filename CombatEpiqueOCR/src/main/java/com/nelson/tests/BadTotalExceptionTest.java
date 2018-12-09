package com.nelson.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.nelson.personnages.BadTotalException;
import com.nelson.personnages.Mage;
import com.nelson.personnages.Personnage;
import com.nelson.personnages.Rodeur;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class BadTotalExceptionTest {

    /**
     * Testing one single type of Player from here : Prowler. Maybe consider making tests for Warrior and Wizard or
     * other if profile creations use different setters from a child character class to another
     */
    @Test
    public void Given_StrengthIntelligenceSkillsGreaterThanLevel_When_Set_Then_ThrowException() {
        Personnage P1 = new Rodeur(1, 0, 0, 0);

        assertThrows(BadTotalException.class, () -> new Rodeur(1, 2, -1, -1).checkTotal(0));
        assertThrows(BadTotalException.class, () -> new Mage(1, 1, 0, -1).checkTotal(1));
    }

    @Test
    public void Given_StrengthIntelligenceSkillsLowerThanLevel_When_Set_Then_ThrowException() {
    	Personnage P1 = new Mage(1, 0, 0, 0);

        assertThrows(BadTotalException.class, () -> new Rodeur(10, 2, -1, 0).checkTotal(7));
    }
    @Test
    public void Given_BadTotal_When_fillingProfile_Then_DisplayGoodSentences() {
    	Personnage P1 = new Rodeur(1, 1,0,0);

        try {
            P1.checkTotal(1);
            P1.checkTotal(10);
            assertThrows(BadTotalException.class, () -> P1.checkTotal(11));
            P1.checkTotal(1);
            P1.checkTotal(10);
            P1.checkTotal(10);
            assertThrows(BadTotalException.class, () -> P1.checkTotal(1));
            P1.checkTotal(1);
            P1.checkTotal(10);
            P1.checkTotal(9);
            P1.checkTotal(0);
            assertThrows(BadTotalException.class, () -> P1.checkTotal(0));
        } catch (BadTotalException e) {
        }
    }
}