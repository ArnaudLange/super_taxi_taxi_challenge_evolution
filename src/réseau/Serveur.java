package réseau;

import jeu.Joueur;

import java.util.ArrayList;

import static jeu.Utils.randInt;

/**
 * Created by Cokral on 03/05/2016.
 */
public class Serveur {

    public Serveur() {
        System.out.println("-------------------");
        System.out.println("Creation du serveur.");
        System.out.println("\tCreation socket.");
        System.out.println("\tServeur à l'ecoute sur le port : 50000.");
        System.out.println("\tServeur en attente de connexions.");
    }

    public void getJoueurs(Joueur j, ArrayList<Joueur> listJoueur){
        System.out.println("Le serveur capte la tentative de connexion d'un client.");
        System.out.println("Il récupère donc le joueur en question : " + j.getNom());
        System.out.println("Le serveur envoie une requête d'initialisation du joueur.");
        j.initialisation();
        System.out.println("Ajout du joueur dans la liste des joueurs.");
        listJoueur.add(j);
    }

    public void gererActionJoueur(Joueur j, String a){
        System.out.println("Réception de l'action \"" + a + "\" du joueur \"" + j.getNom() + "\" par le serveur.");
    }

}
