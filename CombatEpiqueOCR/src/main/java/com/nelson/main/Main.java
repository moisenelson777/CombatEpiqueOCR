package com.nelson.main;

import com.nelson.personnages.Guerrier;
import com.nelson.personnages.Mage;
import com.nelson.personnages.Personnage;
import com.nelson.personnages.Rodeur;

public class Main {

    /**
     * Well if they want to play talking english, it's ok for me. Sorry bros and sis, english only, except for the menu,
     * characters actions and talking, for it to match scenarioOC.png.
     * Since there is no remote repo, there won't be any md file, so please read the first commit for first
     * specifications. Enjoy.
     */
    public static void main(String[] args) {
        // Instanciation des personnages
        Personnage  P1 = new Guerrier(0,0,0,0);
        Personnage  P2 = new Mage(0,0,0,0);

        // Génération des personnages
        System.out.println("Création du personnage du Joueur 1");
        switch(Personnage.choixTypePersonnage())
        {
            case "Guerrier":
                P1 = new Guerrier("Joueur 1");
                break;
            case "Rodeur":
                P1 = new Rodeur("Joueur 1");
                break;
            case "Mage":
                P1 = new Mage("Joueur 1");
                break;
        }

        System.out.println("Création du personnage du Joueur 2");
        switch(Personnage.choixTypePersonnage())
        {
            case "Guerrier":
                P2 = new Guerrier("Joueur 2");
                break;
            case "Rodeur":
                P2 = new Rodeur("Joueur 2");
                break;
            case "Mage":
                P2 = new Mage("Joueur 2");
                break;
        }

        // Combat des personnages
        P1.combattre(P2);
    }
}