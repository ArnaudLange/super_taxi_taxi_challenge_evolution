package jeu;

import carte.Carte;
import carte.InterpreteurCarte;

/**
 * Created by Cokral on 16/05/2016.
 */
public class Controleur {

    public static void main(String[] args) {

        Carte carte = InterpreteurCarte.Interpreter("fileCarte.txt");
        Jeu jeu = new Jeu(carte);

        System.out.println("Creation de joueurs pour le test, fait manuellement par ces derniers normalement.");

        Joueur j1 = new Joueur("Jose");
    }
}
