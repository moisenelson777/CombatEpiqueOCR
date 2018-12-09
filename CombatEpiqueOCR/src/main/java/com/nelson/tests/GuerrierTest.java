package com.nelson.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.nelson.personnages.Guerrier;
import com.nelson.personnages.Mage;
import com.nelson.personnages.Personnage;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class GuerrierTest {

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
    public void Given_Players_When_MenuShowsForWarriorWithTooLowVitality_Then_DisplayGoodSentence() {
        System.setIn(new ByteArrayInputStream("10\n10\n0\n0\n20\n0\n0\n20\n1\n1\n1\n1\n1\n1\n1\n".getBytes()));

        Personnage P1 = new Guerrier("Joueur 1");
        P1.setForce(100);
        Personnage P2 = new Mage("Joueur 2");

        P1.combattre(P2);

        String[] output = outContent.toString().replace("\r\n", "\n").split("\n");
        assertEquals("Joueur 1 (50 Vitalité) veuillez choisir votre action (1 : Attaque Basique)", output[10]);
    }
}