package com.nelson.personnages;

public class Rodeur extends Personnage {

    public Rodeur(String nom) {
        super(nom);
        typePersonnage = "Rodeur";
        attaqueSpeciale = "Concentration";
        attaqueBasique = "Tir A� l'Arc";
        pointsAttaqueBasique = getAgilite();
    }

    /**
     * utilis�s pour les tests
     *
     * @param niveau
     * @param force
     * @param agilite
     * @param intelligence
     */
    public Rodeur(int niveau, int force, int agilite, int intelligence) {
        super(niveau, force, agilite, intelligence);
        attaqueSpeciale = "Concentration";
    }

    /**
     * Augmente les points d'agilit� et fait le point.
     *
     * @param adversaire valeur personnage adverse
     * @return bref message de mise au point
     */
    @Override
    public String actionAttaqueSpeciale(Personnage adversaire) {
        agilite += (niveau / 2);
        pointsAttaqueBasique = agilite;

        return  this.getNomPersonnage()
                + " utilise "
                + attaqueSpeciale
                + " et gagne "
                + String.valueOf(niveau / 2) + " en agilit�.";
    }

    public String playerDescription() {
        return "Chut je suis le Rodeur " + super.descriptionPersonnage();
    }
}