package carte;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;

/**
 * Created by jimmy on 02/05/16.
 */
public class Carte extends Observable  {

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

    // Prend en paramètre : la vitesse du joueur (pour savoir sur combien de case appliquer la direction,
    // la position en X et en Y du joueur
    // la direction du joueur
    public List<Integer> gestionDeplacements(int vitesse, int posX, int posY, PointCardinal Direction){

        //Initialisation de la position finale du joueur après application et de la liste des points cardinaux de la case observée
        //On renverra null si le joueur a fait une action impossible
        List<Integer> positionFinale = new ArrayList<>();
        List<PointCardinal> directionCases = new ArrayList<>();

        //on va effectuer l'algorithme x fois avec x la vitesse
        for (int i = 0; i < vitesse ; i++) {
            System.out.println("Position : "+posX+", "+posY);

            // La carte est codée de façon : tableau[ligne][colonne] donc on inverse posX et posY
            if (tableau[posY][posX] instanceof Route){
                //Si c'est une case route on récupère les points cardinaux
                directionCases = ((List<PointCardinal>)((Route)tableau[posY][posX]).getDirections());
            }
            else {
                //Sinon ce n'est pas une route, on a un accident, retourne null
                System.out.println("Not a Route case.");
                return null;
            }

            //Ensuite on effectue les tests pour chaque direction du joueur.
            if (Direction.equals(PointCardinal.SOUTH)) {
                if (directionCases.contains(PointCardinal.SOUTH)){
                    System.out.println("Déplacement vers le sud effectué.");
                    posY--;
                    if (directionCases.contains(PointCardinal.WEST)){
                        System.out.println("Priorite a droite, il faut ping le controleur.");

                        //On indique qu'il y a une priorité à droite.
                        setChanged();
                        notifyObservers();

                    }
                } else {
                    System.out.println("Can't go this way, sir.");
                    return null;
                }
            }
            else if (Direction.equals(PointCardinal.NORTH)){
                if (directionCases.contains(PointCardinal.NORTH)){
                    System.out.println("Déplacement vers le nord effectué.");
                    posY++;
                    if (directionCases.contains(PointCardinal.EAST)){
                        System.out.println("Priorite a droite, il faut ping le controleur.");

                        //On indique qu'il y a une priorité à droite.
                        setChanged();
                        notifyObservers();
                    }
                } else {
                    System.out.println("Can't go this way, sir.");
                    return null;
                }
            }
            else if (Direction.equals(PointCardinal.EAST)){
                if (directionCases.contains(PointCardinal.EAST)){
                    System.out.println("Déplacement vers l'est effectué.");
                    posX++;
                    if (directionCases.contains(PointCardinal.SOUTH)){
                        System.out.println("Priorite a droite, il faut ping le controleur.");

                        //On indique qu'il y a une priorité à droite.
                        setChanged();
                        notifyObservers();
                    }
                } else {
                    System.out.println("Can't go this way, sir.");
                    return null;
                }
            }
            else if (Direction.equals(PointCardinal.WEST)){
                if (directionCases.contains(PointCardinal.WEST)){
                    System.out.println("Déplacement vers l'ouest effectué.");
                    posX--;
                    if (directionCases.contains(PointCardinal.NORTH)){
                        System.out.println("Priorite a droite, il faut ping le controleur.");

                        //On indique qu'il y a une priorité à droite.
                        setChanged();
                        notifyObservers();
                    }
                } else {
                    System.out.println("Can't go this way, sir.");
                    return null;
                }
            }
        }
        positionFinale.add(posX);
        positionFinale.add(posY);
        return positionFinale;
    }
}
