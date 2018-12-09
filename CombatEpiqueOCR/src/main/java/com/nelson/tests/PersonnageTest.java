package com.nelson.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.nelson.personnages.Guerrier;
import com.nelson.personnages.Mage;
import com.nelson.personnages.Personnage;
import com.nelson.personnages.Rodeur;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PersonnageTest {



    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(System.out);
    }

    /**
     * Doing my best to get close to an exhaustive set of tests here.
     * No Writer class for the moment, consider implementing one if you plan making significant upgrades for extended
     * writing
     */

    @Test
    public void Given_AnswersWithOneError_When_CreateCharacterProfile_Then_DisplayGoodSentences() {
        System.setIn(new ByteArrayInputStream("0\n1\n10\n10\n0\n0\n3\n10\n0\n0\n10\n2\n10\n0\n10\n0\n".getBytes()));
        Personnage.sc = new Scanner(System.in);

        System.out.println("CrÃ©ation du personnage du Joueur 1");
        Personnage.choixTypePersonnage();
        Personnage P1 = new Guerrier("");
        System.out.println("Création du personnage du Joueur 2");
        Personnage.choixTypePersonnage();
        Personnage P2 = new Mage("");
        System.out.println("Création du personnage du Joueur 3");
        Personnage.choixTypePersonnage();
        Personnage P3 = new Rodeur("");



        String[] output = outContent.toString().replace("\r\n", "\n").split("\n");

        assertEquals("Force du personnage ?", output[4]);
        assertEquals("Agilité du personnage ?", output[12]);
        assertEquals("Intelligence du personnage ?", output[20]);
    }

    @Test
    public void Given_Character_When_CreationIsComplete_Then_DisplayGoodSentence() {
        System.setIn(new ByteArrayInputStream("10\n10\n0\n0\n10\n0\n10\n0\n10\n0\n0\n10\n".getBytes()));
        Personnage.sc = new Scanner(System.in);
        Personnage P1 = new Guerrier("Joueur 1");
        Personnage P2 = new Rodeur("Joueur 3");
        Personnage P3 = new Mage("Joueur 2");

        String[] output = outContent.toString().replace("\r\n", "\n").split("\n");
        assertEquals("Woarg je suis le Guerrier Joueur 1 niveau 10 je possÃ¨de 50 de vitalité, 10 de force, 0 d'agilité et 0 d'intelligence !", output[4]);
        assertEquals("Chut je suis le Rodeur Joueur 3 niveau 10 je possède 50 de vitalité, 0 de force, 10 d'agilité et 0 d'intelligence !", output[9]);
        assertEquals("Abracadabra je suis le Mage Joueur 2 niveau 10 je possÃ¨de 50 de vitalité, 0 de force, 0 d'agilité et 10 d'intelligence !", output[14]);
    }

    @Test
    public void Given_Players_When_P1AttacksDeadP2_Then_DisplayGoodSentences() {
        System.setIn(new ByteArrayInputStream("10\n10\n0\n0\n10\n0\n0\n10\n1\n1\n2\n2\n2\n1\n2\n".getBytes()));
        Personnage.sc = new Scanner(System.in);
        Personnage P1 = new Guerrier("Joueur 1");
        Personnage P2 = new Mage("Joueur 2");

        P1.combattre(P2);

        String[] output = outContent.toString().replace("\r\n", "\n").split("\n");
        assertEquals("Joueur 2 est mort", output[33]);
        assertEquals("Joueur 2 a perdu !", output[34]);
    }

    @Test
    public void Given_Players_When_P1AttacksP2_Then_DisplayGoodSentences() {
        System.setIn(new ByteArrayInputStream("10\n10\n0\n0\n10\n0\n0\n10\n1\n1\n2\n2\n2\n1\n2\n".getBytes()));
        Personnage.sc = new Scanner(System.in);
        Personnage P1 = new Guerrier("Joueur 1");
        Personnage P2 = new Mage("Joueur 2");

        P1.combattre(P2);

        String[] output = outContent.toString().replace("\r\n", "\n").split("\n");
        assertEquals("Joueur 1 (50 Vitalité) veuillez choisir votre action (1 : Attaque Basique, 2 : Attaque Spéciale)", output[10]);
        assertEquals("Joueur 1 utilise Coup d'épée et inflige 10 dommages.", output[11]);
        assertEquals("Joueur 2 perd 10 points de vie", output[12]);
        assertEquals("Joueur 2 (40 Vitalité) veuillez choisir votre action (1 : Attaque Basique, 2 : Attaque Spéciale)", output[13]);
    }

    @Test
    public void Given_Players_When_P2AttacksP1_Then_DisplayGoodSentence() {
        System.setIn(new ByteArrayInputStream("10\n10\n0\n0\n10\n0\n0\n10\n1\n1\n2\n2\n2\n1\n2\n".getBytes()));
        Personnage.sc = new Scanner(System.in);
        Personnage P1 = new Guerrier("Joueur 1");
        Personnage P2 = new Mage("Joueur 2");

        P1.combattre(P2);

        String[] output = outContent.toString().replace("\r\n", "\n").split("\n");
        assertEquals("Joueur 2 utilise Boule de Feu et inflige 10 dommages.", output[14]);
        assertEquals("Joueur 1 perd 10 points de vie", output[15]);
        assertEquals("Joueur 1 (40 Vitalité) veuillez choisir votre action (1 : Attaque Basique, 2 : Attaque Spéciale)", output[16]);
    }

    @Test
    public void Given_Players_When_P1SpecialAttacksP2_Then_DisplayGoodSentence() {
        System.setIn(new ByteArrayInputStream("10\n10\n0\n0\n10\n0\n0\n10\n1\n1\n2\n2\n2\n1\n2\n".getBytes()));
        Personnage.sc = new Scanner(System.in);


        Personnage P1 = new Guerrier("Joueur 1");
        Personnage P2 = new Mage("Joueur 2");

        P1.combattre(P2);

        String[] output = outContent.toString().replace("\r\n", "\n").split("\n");
        assertEquals("Joueur 1 utilise Coup de Rage et inflige 20 dommages.", output[17]);
        assertEquals("Joueur 2 perd 20 points de vie", output[18]);
        assertEquals("Joueur 1 perd 5 points de vie", output[19]);
        assertEquals("Joueur 2 (20 VitalitÃ©) veuillez choisir votre action (1 : Attaque Basique, 2 : Attaque SpÃ©ciale)", output[20]);
    }

    @Test
    public void Given_Players_When_P2SpecialAttacksP1_Then_DisplayGoodSentence() {

        System.setIn(new ByteArrayInputStream("10\n10\n0\n0\n10\n0\n0\n10\n1\n1\n2\n2\n2\n1\n2\n".getBytes()));
        Personnage.sc = new Scanner(System.in);
        Personnage P1 = new Guerrier("Joueur 1");
        Personnage P2 = new Mage("Joueur 2");

        P1.combattre(P2);

        String[] output = outContent.toString().replace("\r\n", "\n").split("\n");
        assertEquals("Joueur 2 utilise Soin et gagne 20 en vitalité.", output[21]);
        assertEquals("Joueur 1 (35 Vitalité) veuillez choisir votre action (1 : Attaque Basique, 2 : Attaque Spéciale)", output[22]);
    }
}