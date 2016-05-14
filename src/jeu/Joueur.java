package jeu;

/**
 * Created by jimmy on 02/05/16.
 */
public class Joueur
{

    private static int next_id=1;
    private int id;
    private String nom;
    private int nbPointsPermis;
    private int nbPoints;
    private int vitesse;
    private boolean etatMarche;
    private char direction; // N S E W pour représenter chaque point cardinal

    public Joueur(String nom,int nbPoints,int nbPointsPermis, int vitesse, boolean etatMarche, char direction){
        this.id = next_id++;
        this.nom = nom;
        this.nbPoints = nbPoints;
        this.nbPointsPermis = nbPointsPermis;
        this.etatMarche = etatMarche;
        this.vitesse = vitesse;
        this.direction = direction;
    }


}
