package jeu;

import carte.Carte;
import carte.InterpreteurCarte;
import réseau.Serveur;

import java.util.ArrayList;

/**
 * Created by Cokral on 16/05/2016.
 */
public class Controleur {

    public static void main(String[] args) {

        Carte carte = InterpreteurCarte.Interpreter("fileCarte.txt");
        Jeu jeu = new Jeu(carte);
        Serveur serv = new Serveur();


        Joueur j1 = new Joueur("Jose");
        serv.getJoueurs(j1, (ArrayList<Joueur>) jeu.getListeJoueurs());
        Joueur j2 = new Joueur("René");
        serv.getJoueurs(j2, (ArrayList<Joueur>) jeu.getListeJoueurs());
        Joueur j3 = new Joueur("Jaja");
        serv.getJoueurs(j3, (ArrayList<Joueur>) jeu.getListeJoueurs());
        Joueur j4 = new Joueur("Joso");
        serv.getJoueurs(j4, (ArrayList<Joueur>) jeu.getListeJoueurs());

        serv.gererActionJoueur(j1, j1.joue());




    }
}
