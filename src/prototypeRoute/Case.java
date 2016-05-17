package prototypeRoute;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cokral on 17/05/2016.
 */

public abstract class Case {

    private int posX;
    private int posY;
    private int vitesseAutorisee;
    private List<Direction> listeDirection;

    public Case(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
        this.listeDirection = new ArrayList<Direction>();
        this.vitesseAutorisee = 3;
    }

    public Case(int posX, int posY, int vitesse) {
        this.posX = posX;
        this.posY = posY;
        this.listeDirection = new ArrayList<Direction>();
        this.vitesseAutorisee = vitesse;
    }

    public abstract boolean checkAction(Joueur j);

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public List<Direction> getListeDirection() {
        return listeDirection;
    }

    public void addDirection(Direction dire){
        this.listeDirection.add(dire);
    }

    public int getVitesseAutorisee() {
        return vitesseAutorisee;
    }
}
