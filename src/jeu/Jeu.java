package jeu;

import carte.Carte;

/**
 * Created by Cokral on 16/05/2016.
 */
public class Jeu {

    private Carte carte;
    private Joueur gagnant;
    private int posXObj;
    private int posYObj;

    public Jeu(Carte carte) {

        System.out.println("Creation de la partie.");
        System.out.println("Initialisation de toutes les données.");
        System.out.println("Carte...");
        this.carte=carte;

        System.out.println("Position de l'objectif...");
        this.posXObj=15;
        this.posYObj=15;

    }
}
