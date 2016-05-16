package jeu;

import static jeu.Utils.randInt;

/**
 * Created by jimmy on 02/05/16.
 */
public class Joueur {

    private int id;
    private String nom;
    private int nbPoints;
    private int vitesse;
    private int posX;
    private int posY;
    private boolean etatMarche;
    private char direction; // N S E W pour représenter chaque point cardinal

    public Joueur(String nom){
        System.out.println("-------------------");
        System.out.println("Creation du joueur : " + nom);
        this.nom = nom;
    }

    public void initialisation(){
        System.out.println("Initialisation du joueur : "+this.nom);
        this.nbPoints = 12;
        this.vitesse = 0;
        this.etatMarche = true;
        //on met une position random pour le moment
        this.posX=randInt(30);
        this.posY=randInt(30);
    }

    public String getNom() {
        return nom;
    }

    public void reinitialiseInfo(int nbPoints, int vitesse, boolean etat, int posX, int posY){
        System.out.println("Mise à jour des données sur le joueur : " + this.nom);
        this.nbPoints=nbPoints;
        this.vitesse = vitesse;
        this.etatMarche=etat;
        this.posX = posX;
        this.posY=posY;
    }

    public String joue(){
        String action="";
        System.out.println("Le joueur " + nom + " joue son tour.");
        int rand=randInt(5);
        if (rand == 1){
            System.out.println("Le joueur choisit de ralentir.");
            action = "ralentir";
        }
        else if (rand == 2){
            System.out.println("Le joueur choisit d'accélérer.");
            action = "accelerer";
        }
        else if (rand == 3){
            System.out.println("Le joueur choisit de ne rien faire.");
            action = "rien";
        }
        else if (rand == 4){
            System.out.println("Le joueur choisit de tourner à droite.");
            action = "virageD";
        }
        else if (rand == 5){
            System.out.println("Le joueur choisit de tourner à gauche.");
            action = "virageG";
        }
        return action;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public int getNbPoints() {
        return nbPoints;
    }

    public int getVitesse() {
        return vitesse;
    }

    public boolean isEtatMarche() {
        return etatMarche;
    }
}
