package jeu;

import carte.Carte;
import carte.InterpreteurCarte;
//import jdk.nashorn.internal.scripts.JO;
import reseau.Serveur;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by jimmy on 14/05/16.
 */
public class Controlleur {



    public static void main(String[] args)
    {
        File fichierCarte = new File("src/carte/carte001.txt"); // on initialise le fichier texte de la carte
        Carte carte = InterpreteurCarte.Interpreter(fichierCarte);
        Jeu jeu = new Jeu(new ArrayList<Joueur>(), carte);

        Serveur.creerServeur(jeu.getListeJoueurs());




    }
}

