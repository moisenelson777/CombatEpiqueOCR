package com.nelson.personnages;

public class Mage extends Personnage {

    private int vitaliteMax;

    public Mage(String nom) {
        super(nom);
        typePersonnage = "Mage";
        attaqueSpeciale = "Soin";
        attaqueBasique = "Boule de Feu";
        pointsAttaqueBasique = getIntelligence();
        vitaliteMax = niveau * 5;
    }

    /**
     * utilisés pour les tests
     *
     * @param niveau
     * @param force
     * @param agilite
     * @param intelligence
     */
    public Mage(int niveau, int force, int agilite, int intelligence) {
        super(niveau, force, agilite, intelligence);
        vitalite = niveau * 5;
        vitaliteMax = vitalite;
        attaqueSpeciale = "Soin";
    }

    /**
     * accroit les point de vitalité, mais <= à vitaliteMax, et fait le point.
     *
     * @param adversaire valeur personnage adverse
     * @return bref message de mise au point
     */
    @Override
    public String actionAttaqueSpeciale(Personnage adversaire) {
        int     ancienneVitalite = getVitalite(),
                gainVitalite;
        setVitalite(Math.min(getVitalite() + getIntelligence() * 2, vitaliteMax));
        gainVitalite = getVitalite() - ancienneVitalite;

        return  this.getNomPersonnage()
                + " utilise "
                + attaqueSpeciale
                + " et gagne "
                + gainVitalite + " en vitalité.";
    }

    public String playerDescription() {
        return "Abracadabra je suis le Mage " + super.descriptionPersonnage();
    }

}