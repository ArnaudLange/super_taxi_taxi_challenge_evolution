package carte;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    public Carte (){
        this.largeur = 0;
        this.longueur = 0;
        this.tableau = null;
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

    public List<Integer> gestionDeplacements(int vitesse, int posX, int posY, PointCardinal Direction){
        List<Integer> positionFinale = new ArrayList<>();
        List<PointCardinal> directionCases = new ArrayList<>();

        for (int i = 0; i < vitesse ; i++) {
            if (tableau[posX][posY] instanceof Route){
                directionCases = ((List<PointCardinal>)((Route)tableau[posX][posY]).getDirections());
            }
            else {
                System.out.println("Not a Route case.");
                return null;
            }
            if (Direction.equals(PointCardinal.SOUTH)) {
                if (directionCases.contains(PointCardinal.SOUTH)){
                    posY--;
                    if (directionCases.contains(PointCardinal.WEST)){
                        System.out.println("Priorite a droite, il faut ping le controleur.");
                    }
                } else {
                    System.out.println("Can't go this way, sir.");
                    return null;
                }
            }
            else if (Direction.equals(PointCardinal.NORTH)){
                if (directionCases.contains(PointCardinal.NORTH)){
                    posY++;
                    if (directionCases.contains(PointCardinal.EAST)){
                        System.out.println("Priorite a droite, il faut ping le controleur.");
                    }
                } else {
                    System.out.println("Can't go this way, sir.");
                    return null;
                }
            }
            else if (Direction.equals(PointCardinal.EAST)){
                if (directionCases.contains(PointCardinal.EAST)){
                    posX++;
                    if (directionCases.contains(PointCardinal.SOUTH)){
                        System.out.println("Priorite a droite, il faut ping le controleur.");
                    }
                } else {
                    System.out.println("Can't go this way, sir.");
                    return null;
                }
            }
            else if (Direction.equals(PointCardinal.WEST)){
                if (directionCases.contains(PointCardinal.WEST)){
                    posX--;
                    if (directionCases.contains(PointCardinal.NORTH)){
                        System.out.println("Priorite a droite, il faut ping le controleur.");
                    }
                } else {
                    System.out.println("Can't go this way, sir.");
                    return null;
                }
            }
        }
        return positionFinale;
    }
}
