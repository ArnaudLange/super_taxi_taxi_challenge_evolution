package reseau;

import jeu.Joueur;

import java.io.IOException;
import java.net.*;
import java.util.List;

public class Serveur {

    public static void creerServeur(List listeJoueur)
    {

        ServerSocket listeningSocket;
        try
        {
            // Création du socket d'écoute
            listeningSocket = new ServerSocket(2009);
            System.out.println("Serveur à l'écoute sur le port " + listeningSocket.getLocalPort());

            while(listeJoueur.size() < 2)
            {
                // Création du prochain joueur relié au client qui se connectera
                Joueur joueur = new Joueur();
                listeJoueur.add(joueur);


                // Attente de la connexion à un client
                Socket socketClient = listeningSocket.accept();

                // Lancement du thread qui gèrera le client avec l'objet Joueur en paramètre
                Thread t = new Thread(new ConnexionClient(socketClient, joueur));
                t.start();
            }
            System.out.println(" On a 2 joueurs ! ");
            long time = System.currentTimeMillis();//temps de depart
            long fin=time + 5000;//temps de fin
            long duree = (fin - time);
            System.out.println("durée = "+ duree/1000);
            while (fin > System.currentTimeMillis()){
                System.out.println(" On rentre dans le timer");
                // Création du prochain joueur relié au client qui se connectera
                Joueur joueur = new Joueur();
                listeJoueur.add(joueur);


                // Attente de la connexion à un client
                Socket socketClient = listeningSocket.accept();

                // Lancement du thread qui gèrera le client avec l'objet Joueur en paramètre
                Thread t = new Thread(new ConnexionClient(socketClient, joueur));
                t.start();
            }
            System.out.println("On sort du timer, la partie va commencer");

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
