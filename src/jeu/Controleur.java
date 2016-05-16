package jeu;

import carte.Carte;
import carte.InterpreteurCarte;
import reseau.ConnexionClient;
import reseau.Serveur;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jimmy on 14/05/16.
 */
public class Controleur
{

    public static void main(String[] args)
    {
        File fichierCarte = new File("src/carte/carte001.txt"); // on initialise le fichier texte de la carte
        Carte carte = InterpreteurCarte.Interpreter(fichierCarte);
        Jeu jeu = new Jeu(new ArrayList<Joueur>(), carte);
        List<ConnexionClient> listeConnexionClient = new ArrayList();

        Serveur.creerServeur(jeu.getListeJoueurs(), listeConnexionClient);
        listeConnexionClient.get(1).envoyerMessage("bonjour");


    }
}

