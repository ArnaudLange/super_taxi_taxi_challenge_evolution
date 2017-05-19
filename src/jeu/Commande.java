package jeu;

import carte.PointCardinal;

import java.awt.*;

/**
 * Created by jimmy on 17/05/16.
 */
public enum Commande
{
    AUCUNE_ACTION(""),
    ERREUR_ACTION("erreuraction"),
    NOM("nom"),
    NEXT_ACTION("action"),
    GAUCHE("gauche"),
    DROITE("Droite"),
    NORD(PointCardinal.NORD.toString()),
    SUD(PointCardinal.SUD.toString()),
    EST(PointCardinal.EST.toString()),
    OUEST(PointCardinal.OUEST.toString()),
    ACCELERER("accelerer"),
    RALENTIR("ralentir"),
    GAMENOTWIN("gamenotwin"),
    GAMEOVER("gameover"),
    GAMEWINOBJ("gamewinobj"),
    GAMEWINLASTPLAYER("gamewinlastplayer");


    private final String text;

    Commande(final String text)
    {
        this.text = text;
    }

    public String toString() {
        return text;
    }

    public static Commande getCommande(String s)
    {
        switch(((s == null)?"":s).toLowerCase())
        {
            case "action":
                return NEXT_ACTION;
            case "gauche":
                return GAUCHE;
            case "droite":
                return DROITE;
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
            case "gamenotwin":
                return GAMENOTWIN;
            case "gameover":
                return GAMEOVER;
            case "gamewinobj":
                return GAMEWINOBJ;
            case "nom":
                return NOM;
            case "gamewinlastplayer":
                return GAMEWINLASTPLAYER;
            case "":
                return AUCUNE_ACTION;
            default:
                    return ERREUR_ACTION;
        }
    }
}

