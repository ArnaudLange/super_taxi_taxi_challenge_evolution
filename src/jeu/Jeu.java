package jeu;

import carte.Carte;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cokral on 16/05/2016.
 */
public class Jeu {

    private List<Joueur> listeJoueurs;
    private Carte carte;
    private Joueur gagnant;
    private int posXObj;
    private int posYObj;

    public Jeu(Carte carte) {

        System.out.println("-------------------");
        System.out.println("Initialisation du jeu.");
        System.out.println("Creation de la partie.");
        System.out.println("Initialisation de toutes les données.");
        System.out.println("\tCarte...");
        this.carte=carte;

        System.out.println("\tPosition de l'objectif...");
        this.posXObj=15;
        this.posYObj=15;

        System.out.println("\tListe de joueurs (vide pour le moment)...");
        this.listeJoueurs=new ArrayList<Joueur>();
    }

    public Carte getCarte() {
        return carte;
    }

    public List<Joueur> getListeJoueurs() {
        return listeJoueurs;
    }
}
