package carte;

import jeu.Joueur;

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
    public void gestionDeplacements(Joueur j){
        boolean prioPossible;
        List<PointCardinal> directionCases = new ArrayList<>();

        //on va effectuer l'algorithme x fois avec x la vitesse
        for (int i = 0; i < j.getVitesse() ; i++) {
            prioPossible=false;
            // La carte est codée de façon : tableau[ligne][colonne] donc on inverse posX et posY
            if (tableau[j.getPosY()][j.getPosX()] instanceof Route){
                //Si c'est une case route on récupère les points cardinaux
                directionCases = ((List<PointCardinal>)((Route)tableau[j.getPosY()][j.getPosX()]).getDirections());
            }
            else {
                //Sinon ce n'est pas une route, on a un accident, retourne null
                System.out.println("Not a Route case.");
                return;
            }

            if(directionCases.size()==2){
                if(!directionCases.contains(j.getDirection())){
                    for (PointCardinal dir:directionCases) {
                        if(j.getDirection().equals(PointCardinal.EAST)){
                            if (dir!=PointCardinal.WEST){
                                j.setDirection(dir);
                            }
                        }
                        else if(j.getDirection().equals(PointCardinal.NORTH)){
                            if (dir!=PointCardinal.SOUTH){
                                j.setDirection(dir);
                            }
                        }
                        if(j.getDirection().equals(PointCardinal.SOUTH)){
                            if (dir!=PointCardinal.NORTH){
                                j.setDirection(dir);
                            }
                        }
                        if(j.getDirection().equals(PointCardinal.WEST)){
                            if (dir!=PointCardinal.EAST){
                                j.setDirection(dir);
                            }
                        }
                    }
                }
            }
            else if (directionCases.size()>2){
                prioPossible=true;
            }

            //Ensuite on effectue les tests pour chaque direction du joueur.
            if (j.getDirection().equals(PointCardinal.SOUTH)) {
                if (directionCases.contains(PointCardinal.SOUTH)){
                    System.out.println("Déplacement vers le sud effectué.");
                    j.setPosY(j.getPosY()+1);
                    if (directionCases.contains(PointCardinal.WEST) && prioPossible){
                        System.out.println("Priorite a droite, il faut ping le controleur.");

                        //On indique qu'il y a une priorité à droite.
                        setChanged();
                        notifyObservers(j);

                    }
                } else {
                    System.out.println("Can't go this way, sir.");
                    return;
                }
            }
            else if (j.getDirection().equals(PointCardinal.NORTH)){
                if (directionCases.contains(PointCardinal.NORTH)){
                    System.out.println("Déplacement vers le nord effectué.");
                    j.setPosY(j.getPosY()-1);
                    if (directionCases.contains(PointCardinal.EAST)&& prioPossible){
                        System.out.println("Priorite a droite, il faut ping le controleur.");

                        //On indique qu'il y a une priorité à droite.
                        setChanged();
                        notifyObservers(j);
                    }
                } else {
                    System.out.println("Can't go this way, sir.");
                    return;
                }
            }
            else if (j.getDirection().equals(PointCardinal.EAST)){
                if (directionCases.contains(PointCardinal.EAST)){
                    System.out.println("Déplacement vers l'est effectué.");
                    j.setPosX(j.getPosX()+1);
                    if (directionCases.contains(PointCardinal.SOUTH)&& prioPossible){
                        System.out.println("Priorite a droite, il faut ping le controleur.");

                        //On indique qu'il y a une priorité à droite.
                        setChanged();
                        notifyObservers(j);
                    }
                } else {
                    System.out.println("Can't go this way, sir.");
                    return;
                }
            }
            else if (j.getDirection().equals(PointCardinal.WEST)){
                if (directionCases.contains(PointCardinal.WEST)){
                    System.out.println("Déplacement vers l'ouest effectué.");
                    j.setPosX(j.getPosX()-1);
                    if (directionCases.contains(PointCardinal.NORTH)&& prioPossible){
                        System.out.println("Priorite a droite, il faut ping le controleur.");

                        //On indique qu'il y a une priorité à droite.
                        setChanged();
                        notifyObservers(j);
                    }
                } else {
                    System.out.println("Can't go this way, sir.");
                    return;
                }
            }
            System.out.println("Position : "+j.getPosX()+","+j.getPosY());
        }

    }
}
