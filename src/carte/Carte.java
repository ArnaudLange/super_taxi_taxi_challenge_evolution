package carte;

import static jeu.Utils.randInt;

/**
 * Created by jimmy on 02/05/16.
 */
public class Carte {

    public Carte(){
        System.out.println("Carte créée");
    }

    public Case getCase(int posX, int posY){
        System.out.println("Recherche de la case où est située le joueur.");
        int rand=randInt(4);
        if (rand == 1){
            System.out.println("\tC'est un pont.");
            return new Pont();
        }
        else if (rand == 2){
            System.out.println("\tC'est une route droite.");
            return new Route();
        }
        else if (rand == 3){
            System.out.println("\tC'est une route en T.");
            return new RouteEnT();
        }
        else if (rand == 4){
            System.out.println("\tC'est un virage.");
            return new Virage();
        }
        System.out.println("C'est une case neutre.");
        return new CaseNeutre();
    }
}
