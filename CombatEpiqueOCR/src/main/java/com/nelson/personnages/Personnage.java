package com.nelson.personnages;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The abstract class with methods to be implemented according to the speciality wanted.
 * Its children class names have to be suffixed with the speciality.
 */
public abstract class Personnage {

    protected int niveau = 0;
    protected int force = -1;
    protected int intelligence = -1;
    protected int agilite = -1;
    protected int vitalite = 0;
    private String nomPersonnage;

    protected int pointsAttaqueBasique;
    protected String typePersonnage;
    protected String attaqueBasique;
    protected String attaqueSpeciale;

    public static Scanner sc = new Scanner(System.in);

    protected void init() {
        niveau = 0;
        force = -1;
        intelligence = -1;
        agilite = -1;
    }

    /**
     * ask for a number lt max and gt min, limits allowed. Also throw an exception if NaN.
     * @param min
     * @param max
     * @param question
     * @return
     */
    public static int askForNumber(int min, int max, String question) throws BadNumberException {
        int nombre = min - 1;
        boolean bonneReponse;
        System.out.println(question);
        do {
        	bonneReponse = true;
            try {
            	nombre = sc.nextInt(); // if number still equals -1 ...
            } catch (InputMismatchException e) {
                sc.next();
                System.err.println("Vous devez saisir un nombre, compris entre " + min + " et " + max);
                bonneReponse = false;
            }
            if (bonneReponse && (nombre > max || nombre < min)) throw new BadNumberException(); // ... this exception will be thrown
        } while (nombre > max || nombre < min || !bonneReponse);

        return nombre;
    }

    /**
     * Could have used getClassName but have to translate into french.
     * @return a valid chosen String representing the player type
     */
    public static String choixTypePersonnage() {
        String[] typesPersonnages = {"Guerrier", "Rodeur", "Mage"};
        String typePersonnage = null;
        while (typePersonnage == null) {
            try {
            	typePersonnage = typesPersonnages[askForNumber(1, 3, "Veuillez choisir la classe de votre personnage (1 : Guerrier, 2 : RÃ´deur, 3 : Mage)") - 1];
            } catch (BadNumberException e) {
            }
        }
        return typePersonnage;
    }

    public Personnage(int niveau, int force, int agilite, int intelligence) {
        this.niveau = niveau;
        this.force = force;
        this.agilite = agilite;
        this.intelligence = intelligence;
    }

    public Personnage(String nom) {
    	nomPersonnage = nom;
        boolean recreation;
        do {
            recreation = false;
            try {
                niveau = askForNumber(1, 100, "Niveau du personnage ?");
                force = checkTotal(askForNumber(0, 100, "Force du personnage ?"));
                agilite = checkTotal(askForNumber(0, 100, "Agilité du personnage ?"));
                intelligence = checkTotal(askForNumber(0, 100, "Intelligence du personnage ?"));
                vitalite = niveau * 5;
                System.out.println(descriptionPersonnage());
            } catch (BadTotalException e) {
                recreation = true;
            } catch (BadNumberException | InputMismatchException e) {
            }
        } while (recreation);
    }

    // useful for checkTotal, returns 0 or abs(n)
    private int zeroNeg(int n) {
        return (n < 0) ? 0 : n;
    }

    /**
     * tests the validly submitted characteristics profile point value number according to the specifications
     * @param number the checked point value
     * @return the number once checked
     * @throws BadTotalException
     */
    public int checkTotal(int nombre) throws BadTotalException {
        // number must not take level value
        // when all the characteristics are entered, check total. Otherwise, check the entered number
        if (force >= 0 && force >= 0 && (nombre + force + force != niveau) || (nombre > niveau)) {
            init();
            throw new BadTotalException();
        }
        //check total when any characteristic is entered
        else if ((force >= 0 || force >= 0) && (zeroNeg(intelligence) + zeroNeg(force) + zeroNeg(agilite) + nombre) > niveau) {
            init();
            throw new BadTotalException();
        }
        return nombre;
    }


    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    public String getNomPersonnage() {
        return nomPersonnage;
    }

    public void setNomPersonnage(String nomPersonnage) {
        this.nomPersonnage = nomPersonnage;
    }

    public String getAttaqueBasique() {
        return attaqueBasique;
    }

    public void setPointsAttaqueBasique(int points) {
    	pointsAttaqueBasique = points;
    }

    public String getAttaqueSpeciale() {
        return attaqueSpeciale;
    }

    public int getForce() {
        return force;
    }

    public void setForce(int force) {
        this.force = force;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getPointsAttaqueBasique() {
        return pointsAttaqueBasique;
    }

    public int getAgilite() {
        return agilite;
    }

    public void setAgilite(int agilite) {
        this.agilite = agilite;
    }

    public int getVitalite() {
        return vitalite;
    }

    public void setVitalite(int vitalite) {
        this.vitalite = vitalite;
    }

    public String descriptionPersonnage() {
        return  nomPersonnage
                + " niveau " + String.valueOf(niveau)
                + " je possÃ¨de " + String.valueOf(vitalite) + " de vitalité, "
                + String.valueOf(force) + " de force, "
                + String.valueOf(agilite) + " d'agilité et "
                + String.valueOf(intelligence) + " d'intelligence !";
    }

    public String actionAttaqueBasique(Personnage enemie) {
    	enemie.setVitalite(enemie.getVitalite() - pointsAttaqueBasique);

        return  nomPersonnage
                + " utilise "
                + attaqueBasique
                + " et inflige "
                + pointsAttaqueBasique
                + " dommages." + String.format("%n")
                + enemie.getNomPersonnage()
                + " perd "
                + pointsAttaqueBasique
                + " points de vie"
                + enemie.checkSante();
    }

    public abstract String actionAttaqueSpeciale(Personnage enemie);

    /**
     * returns a flashMessage if vitality <= 0
     * @return the flash message
     */
    public String checkSante() {
        return (vitalite > 0) ? "" : String.format("%n" + nomPersonnage + " est mort");
    }


    public int menu() {
        int reponse = 0;

        System.out.print(nomPersonnage + " (" + vitalite + " Vitalité) veuillez choisir votre action ");

        try {
        if (typePersonnage != "Guerrier" || vitalite > force / 2) {
                reponse = askForNumber(1, 2, "(1 : Attaque Basique, 2 : Attaque Spéciale)");
        }
        else
        	reponse = askForNumber(1,1, "(1 : Attaque Basique)");
        } catch (BadNumberException e) {}

        return reponse;
    }

    public void combattre(Personnage adversaire) {
        while (vitalite > 0 && adversaire.vitalite > 0) {
            if (menu() == 1) System.out.println(actionAttaqueBasique(adversaire));
            else System.out.println(actionAttaqueSpeciale(adversaire));

            if (adversaire.vitalite > 0)
                if (adversaire.menu() == 1) System.out.println(adversaire.actionAttaqueBasique(this));
                else System.out.println(adversaire.actionAttaqueSpeciale(this));
            else {
                System.out.println(adversaire.nomPersonnage + " a perdu !");
                return;
            }
        }
        System.out.println(nomPersonnage + " a perdu !");
    }
}