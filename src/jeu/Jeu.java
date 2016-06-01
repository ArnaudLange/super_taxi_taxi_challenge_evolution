package jeu;

import carte.Carte;

import java.util.List;

/**
 * Created by swag on 03/05/16.
 */
public class Jeu
{
    private List<Joueur> listeJoueurs;
    private Carte carte;
    private Joueur gagnant ;
    private int posXObjectif;
    private int posYObjectif;

    public Jeu(List<Joueur> listeJoueurs, Carte carte)
    {
        this.listeJoueurs = listeJoueurs;
        this.carte = carte;
    }


    public Carte getCarte() {
        return carte;
    }


    public Joueur getGagnant()
    {
        return gagnant;
    }

    public void setGagnant(Joueur gagnant)
    {
        this.gagnant = gagnant;
    }

    public List<Joueur> getListeJoueurs() {
        return listeJoueurs;
    }

    public int getPosXObjectif() {
        return posXObjectif;
    }

    public int getPosYObjectif() {
        return posYObjectif;
    }
}
