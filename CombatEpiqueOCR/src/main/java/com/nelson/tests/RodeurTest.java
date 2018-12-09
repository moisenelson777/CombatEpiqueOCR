package com.nelson.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.nelson.personnages.Guerrier;
import com.nelson.personnages.Personnage;
import com.nelson.personnages.Rodeur;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RodeurTest {
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
    public void Given_Answers_When_AskingForAProwler_Then_SetGoodPlayerType() {
        System.setIn(new ByteArrayInputStream("2\n".getBytes()));

        assertEquals("Rodeur", Personnage.choixTypePersonnage());
    }

    @Test
    public void Given_Answers_When_SpecialAttack_Then_CheckAttributesValuesAndDisplayGoodSentence() {
        System.setIn(new ByteArrayInputStream("10\n10\n0\n0\n10\n0\n10\n0\n".getBytes()));
        Personnage P1 = new Guerrier(10, 10, 0, 0);
        Personnage P2 = new Rodeur(10, 0, 0, 10);

        String[] output = outContent.toString().replace("\r\n", "\n").split("\n");
        assertEquals("null utilise Concentration et gagne 5 en agilité.", P2.actionAttaqueSpeciale(P1));
        assertEquals(15, P2.getAgilite());
    }
}