package com.nelson.personnages;

public class Guerrier extends Personnage {

    public Guerrier(String nom) {
        super(nom);
        typePersonnage = "Guerrier";
        attaqueSpeciale = "Coup de Rage";
        attaqueBasique = "Coup d'épée";
        pointsAttaqueBasique = getForce();
    }

    /**
     * used for tests
     *
     * @param niveau
     * @param force
     * @param agilite
     * @param intelligence
     */
    public Guerrier(int niveau, int force, int agilite, int intelligence) {
        super(niveau, force, agilite, intelligence);
    }

    /**
     * take vitality points down and say so.
     * Take enemy vitality down by strength * 2 and say so.
     *
     * @param enemy
     * @return this flash message
     */
    @Override
    public String actionAttaqueSpeciale(Personnage adversaire) {
        String pointsAttaqueSpeciale = String.valueOf(getForce() * 2);
        setVitalite(getVitalite() - (getForce() / 2));
        adversaire.setVitalite(adversaire.getVitalite() - (getForce() * 2));

        return  this.getNomPersonnage()
                + " utilise "
                + attaqueSpeciale
                + " et inflige "
                + pointsAttaqueSpeciale
                + " dommages." + String.format("%n")
                + adversaire.getNomPersonnage()
                + " perd "
                + pointsAttaqueSpeciale
                + " points de vie" + String.format("%n")
                + getNomPersonnage()
                + " perd "
                + getForce() / 2
                + " points de vie"
                + adversaire.checkSante();
    }

    public String descriptionPersonnage() {
        return "Woarg je suis le Guerrier " + super.descriptionPersonnage();
    }

}