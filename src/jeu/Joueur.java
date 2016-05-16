package jeu;

/**
 * Created by jimmy on 02/05/16.
 */
public class Joueur {

    private int id;
    private String nom;
    private int nbPoints;
    private int vitesse;
    private boolean etatMarche;
    private char direction; // N S E W pour représenter chaque point cardinal

    public Joueur(String nom){
        this.nom = nom;
    }

    public void initialisation(){
        System.out.println("Initialisation du joueur : "+this.nom);
        this.nbPoints = 12;
        this.vitesse = 0;
        this.etatMarche = true;
        //on met une position random pour le moment
        this.direction = 'N';
    }


}
