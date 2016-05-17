package jeu;

/**
 * Created by jimmy on 17/05/16.
 */
public enum  Action {
    RIEN,
    NORD,
    SUD,
    EST,
    OUEST,
    ACCELERER,
    RALENTIR;

public static Action getAction(String s){

    switch(s.toLowerCase()) {
        case "rien":
            return RIEN;
        case "nord":
            return NORD;
        case "sud":
            return SUD;
        case "est" :
            return EST;
        case "ouest" :
            return OUEST;
        case "accelerer":
            return ACCELERER;
        case "ralentir":
            return RALENTIR;
        default:
            return null;
        }
    }
}

