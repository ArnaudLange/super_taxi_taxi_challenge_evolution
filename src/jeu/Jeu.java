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

    public Jeu(List<Joueur> listeJoueurs, int largeurCarte, int longueurCarte)
    {
        this.listeJoueurs = listeJoueurs;
        this.carte = new Carte(longueurCarte, largeurCarte);
    }
}
