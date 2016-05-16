package jeu;

import carte.Carte;
import carte.InterpreteurCarte;

/**
 * Created by Cokral on 16/05/2016.
 */
public class Controleur {

    public static void main(String[] args) {

        System.out.println("Test");

        Carte carte = new InterpreteurCarte("file.txt");
        Jeu jeu = new Jeu(carte);
    }
}
