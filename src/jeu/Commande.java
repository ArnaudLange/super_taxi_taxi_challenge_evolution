package jeu;

/**
 * Created by jimmy on 17/05/16.
 */
public enum Commande {
    AUCUNE_ACTION("rien"),
    ERREUR_ACTION("erreuraction"),
    NEXT_ACTION("action"),
    NORD("nord"),
    SUD("sud"),
    EST("est"),
    OUEST("ouest"),
    ACCELERER("accelerer"),
    RALENTIR("ralentir"),
    GAMEOVER("gameover");

    private final String text;

    Commande(final String text)
    {
        this.text = text;
    }

    public String toString() {
        return text;
    }

    public static Commande getAction(String s){

    switch(s.toLowerCase()) {
        case "action":
            return NEXT_ACTION;
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
        case "gameover":
            return GAMEOVER;
        case "":
            return AUCUNE_ACTION;
        default:
            return ERREUR_ACTION;
        }
    }
}

