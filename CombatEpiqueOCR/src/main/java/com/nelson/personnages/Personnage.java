package com.nelson.personnages;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * La classe abstraite dotée de methodes d'implémentation selon les spécifications souhaitées.
 * Chaque classe enfants developpera un comportement particulier.
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
     * demande un nombre dans les limites permises sinon lève une exception.
     * @param min
     * @param max
     * @param question
     * @return
     */
    public static int demandeUnNombre(int min, int max, String question) throws BadNumberException {
        int nombre = min - 1;
        boolean bonneReponse;
        System.out.println(question);
        do {
        	bonneReponse = true;
            try {
            	nombre = sc.nextInt(); // si nombre est toujours égal à -1 ...
            } catch (InputMismatchException e) {
                sc.next();
                System.err.println("Vous devez saisir un nombre, compris entre " + min + " et " + max);
                bonneReponse = false;
            }
            if (bonneReponse && (nombre > max || nombre < min)) throw new BadNumberException(); // ... Cette exception sera levée
        } while (nombre > max || nombre < min || !bonneReponse);

        return nombre;
    }

    /**
     * Nous aurions toute fois pu invoqué getClassName pour identifier la classe du Personnage.
     * @return un choix String valide représentant le type personnage
     */
    public static String choixTypePersonnage() {
        String[] typesPersonnages = {"Guerrier", "Rodeur", "Mage"};
        String typePersonnage = null;
        while (typePersonnage == null) {
            try {
            	typePersonnage = typesPersonnages[demandeUnNombre(1, 3, "Veuillez choisir la classe de votre personnage (1 : Guerrier, 2 : Rodeur, 3 : Mage)") - 1];
            } catch (BadNumberException e) {
            }
        }
        return typePersonnage;
    }

    
    /**Constructeur Personnage avec les attributs de combat en paramètre.
     * @param niveau
     * @param force
     * @param agilite
     * @param intelligence
     */
    public Personnage(int niveau, int force, int agilite, int intelligence) {
        this.niveau = niveau;
        this.force = force;
        this.agilite = agilite;
        this.intelligence = intelligence;
    }

    
    /**Constructeur Personnage avec le nom en attribut
     * @param nom
     */
    public Personnage(String nom) {
    	nomPersonnage = nom;
        boolean recreation;
        do {
            recreation = false;
            try {
                niveau = demandeUnNombre(1, 100, "Niveau du personnage ?");
                force = checkTotal(demandeUnNombre(0, 100, "Force du personnage ?"));
                agilite = checkTotal(demandeUnNombre(0, 100, "Agilité du personnage ?"));
                intelligence = checkTotal(demandeUnNombre(0, 100, "Intelligence du personnage ?"));
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
     * Teste la validité des caractères soumis selon les spécifications attribuées au personnage
     * @param nombre la valeur à verifiée 
     * @return le nombre une fois vérifiée
     * @throws BadTotalException
     */
    public int checkTotal(int nombre) throws BadTotalException {
        // nombre doit pas prendre la valeur du niveau personnage
        // Une fois tous les caractères entrés, vérification du total. Autrement, vérification du nombre entré
        if (force >= 0 && agilite >= 0 && (nombre + force + agilite != niveau) || (nombre > niveau)) {
            init();
            throw new BadTotalException();
        }
        //Vérification du total à chaque entrée de caractère
        else if ((force >= 0 || agilite >= 0) && (zeroNeg(intelligence) + zeroNeg(force) + zeroNeg(agilite) + nombre) > niveau) {
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

    /**
     * @return String valeur description du personnage.
     */
    public String descriptionPersonnage() {
        return  nomPersonnage
                + " niveau " + String.valueOf(niveau)
                + " je possède " + String.valueOf(vitalite) + " de vitalité, "
                + String.valueOf(force) + " de force, "
                + String.valueOf(agilite) + " d'agilité et "
                + String.valueOf(intelligence) + " d'intelligence !";
    }

    
    /**
     * @param enemie valeur personnage adverse
     * @return String valeur etat du personnage adversaire suite à l'attaque basique.
     */
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

    
    /**
     * @param enemie valeur personnage adverse
     * @return String valeur etat du personnage adversaire suite à l'attaque spéciale.
     */
    public abstract String actionAttaqueSpeciale(Personnage enemie);

    /**
     * retourne un bref message si la vitalité <= 0
     * @return bref message
     */
    public String checkSante() {
        return (vitalite > 0) ? "" : String.format("%n" + nomPersonnage + " est mort");
    }


    /**methode proposant un panel d'actions à réaliser par le joueur
     * @return int valeur de l'action à réaliser par le joueur
     */
    public int menu() {
        int reponse = 0;

        System.out.print(nomPersonnage + " (" + vitalite + " Vitalité) veuillez choisir votre action ");

        try {
        if (typePersonnage != "Guerrier" || vitalite > force / 2) {
                reponse = demandeUnNombre(1, 2, "(1 : Attaque Basique, 2 : Attaque Spéciale)");
        }
        else
        	reponse = demandeUnNombre(1,1, "(1 : Attaque Basique)");
        } catch (BadNumberException e) {}

        return reponse;
    }

    
    /**methode engageant le combat entre 2 joueurs
     * @param adversaire valeur personnage joueur adverse
     */
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