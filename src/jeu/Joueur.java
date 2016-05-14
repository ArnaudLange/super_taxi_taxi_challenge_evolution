package jeu;

import carte.PointCardinal;

/**
 * Created by jimmy on 02/05/16.
 */
public class Joueur
{

    private static int next_id=1;
    private int id;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    private String nom;
    private int nbPoints;
    private int vitesse;
    private boolean etatMarche;
    private PointCardinal direction; // N S E W pour représenter chaque point cardinal

    public Joueur(String nom,int nbPoints, int vitesse, boolean etatMarche, PointCardinal direction)
    {
        this.id = next_id++;
        this.nom = nom;
        this.nbPoints = nbPoints;;
        this.etatMarche = etatMarche;
        this.vitesse = vitesse;
        this.direction = direction;
    }


}
