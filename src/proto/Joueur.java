package proto;

import static proto.Utils.randInt;

/**
 * Created by Cokral on 03/05/2016.
 */


public class Joueur {

    private String nom;

    public Joueur(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return nom;
    }

    public void joue(){
        int random = randInt(4);
        //1 : ralentir ; 2 : accelerer ; 3 : tourner a droite ; 4 : tourner a gauche
        if(random == 2){
            System.out.println("\til accelere.");
        }
        else if (random==1){
            System.out.println("\til ralenti.");
        }
        else if (random==3){
            System.out.println("\til tourne a droite.");
        }
        else if (random==4){
            System.out.println("\til tourne a gauche.");
        }
    }


}
