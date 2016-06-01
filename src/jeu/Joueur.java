package jeu;

import carte.PointCardinal;

import java.util.List;

/**
 * Created by jimmy on 02/05/16.
 */
public class Joueur
{

    private static int next_id=1;
    private int id;
    private String nom;
    private int nbPoints;
    private int vitesse;
    private boolean etatMarche;
    private int posX ;
    private int posY;
    private PointCardinal direction; // N S E W pour représenter chaque point cardinal


    public Joueur()
    {
        this.id = next_id++;
        this.nom = null;
        this.nbPoints = 10;
        this.etatMarche = true;
        this.vitesse = 0;
        this.direction = null;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }



    public void accelerer(){
        if (vitesse < 3)
        {
            this.vitesse += 1;
        }
    }
    public void ralentir(){
        if (vitesse >0)
        {
            this.vitesse -= 1;
        }
    }

    public int getId()
    {
        return id;
    }

    public PointCardinal getDirection()
    {
        return direction;
    }

    public void setDirection(PointCardinal direction)
    {
        this.direction = direction;
    }

    public boolean isEtatMarche()
    {
        return etatMarche;
    }

    public void setEtatMarche(boolean etatMarche)
    {
        this.etatMarche = etatMarche;
    }

    public int getNbPoints()
    {
        return nbPoints;
    }

    public void setNbPoints(int nbPoints)
    {
        this.nbPoints = nbPoints;
    }

    public String getNom()
    {
        return nom;
    }

    public void setNom(String nom)
    {
        this.nom = nom;
    }

    public int getVitesse()
    {
        return vitesse;
    }

    public void setVitesse(int vitesse)
    {
        this.vitesse = vitesse;
    }

    public void updatePos(List<Integer> position){
        this.setPosX(position.get(0));
        this.setPosY(position.get(1));
        System.out.println("Position du joueur : " + this.nom + "updatée à : "+posX+","+posY);
    }
}
