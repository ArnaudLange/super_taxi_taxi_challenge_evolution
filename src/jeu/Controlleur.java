package jeu;

import carte.Carte;
import carte.InterpreteurCarte;

import java.io.File;

/**
 * Created by jimmy on 14/05/16.
 */
public class Controlleur {



    public static void main(String[] args) {

        Carte carte = new Carte(30,30); // on créer la carte vide
        File fichierCarte = new File("src/carte/carte001.txt"); // on initialise le fichier texte de la carte
        new InterpreteurCarte(carte,fichierCarte) {

        }
    }
}
