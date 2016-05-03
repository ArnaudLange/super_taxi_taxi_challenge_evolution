package carte;

import java.util.Arrays;

/**
 * Created by jimmy on 02/05/16.
 */
public class Carte
{
    private int longueur; //nb de cases en abcisse
    private int largeur; // nb de cases en ordonnée
    private Case[][] tableau;

    public Carte (int largeur, int longueur)
    {
        this.largeur = largeur;
        this.longueur = longueur;
        this.tableau = new Case[this.largeur][this.longueur];
    }

    public int getLargeur()
    {
        return largeur;
    }

    public int getLongueur()
    {
        return longueur;
    }

    public Case[][] getTableau()
    {
        return tableau;
    }

    public void setTableau(Case[][] tableau)
    {
        this.tableau = tableau;
    }

    @Override
    public String toString()
    {
        return "Carte{" +
                "tab=" + Arrays.toString(tableau) +
                '}';
    }

    public Carte(Case [][] tableau)
    {
        this.tableau = tableau;
    }
}
