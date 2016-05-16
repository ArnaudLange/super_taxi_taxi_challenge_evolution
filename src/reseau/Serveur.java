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

            while(true)
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
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
