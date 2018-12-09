package com.nelson.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.nelson.personnages.Guerrier;
import com.nelson.personnages.Mage;
import com.nelson.personnages.Personnage;
import com.nelson.personnages.Player;
import com.nelson.personnages.Warrior;
import com.nelson.personnages.Wizard;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class MageTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(System.out);
    }

    @Test
    public void Given_Players_When_WizardUseSpecialAttackWithTooHighVitality_Then_DisplayGoodSentence() {
    	Personnage P1 = new Mage(10,0,10,0);
        P1.setVitalite(40);
        assertEquals("null utilise Soin et gagne 10 en vitalité.", P1.actionAttaqueSpeciale(new Guerrier(10, 10, 0, 0)));
    }
}