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
     * utilisés pour les tests
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
     * cause des dommages et fait le point.
     * reduit la vitalité de l'adversaire de la force * 2 and et fait le point.
     *
     * @param adversaire valeur personnage adverse
     * @return bref message de mise au point
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