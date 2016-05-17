package jeu;

/**
 * Created by jimmy on 17/05/16.
 */
public enum  Action {
    RIEN,
    GAUCHE,
    DROITE,
    ACCELERER,
    RALENTIR;

public static Action getAction(String s){

    switch(s.toLowerCase()) {
        case "rien":
            return RIEN;
        case "gauche":
            return GAUCHE;
        case "droite":
            return DROITE;
        case "accelerer":
            return ACCELERER;
        case "ralentir":
            return RALENTIR;
        default:
            return null;
        }
    }
}

