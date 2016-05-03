package carte;

import java.util.Arrays;

/**
 * Created by jimmy on 02/05/16.
 */
public class Carte {



    private int longueur; //nb de cases en abcisse

    public int getLargeur() {
        return largeur;
    }

    public int getLongueur() {
        return longueur;
    }

    private int largeur; // nb de cases en ordonnée
    private Case[][] tableau;

    public Case[][] getTableau() {
        return tableau;
    }

    public void setLongueur(int longueur) {
        this.longueur = longueur;
    }

    public void setLargeur(int largeur) {
        this.largeur = largeur;
    }

    @Override
    public String toString() {
        return "Carte{" +
                "tab=" + Arrays.toString(tableau) +
                '}';
    }


    public Carte(Case [][] tableau){
        this.tableau = tableau;
    }
}
