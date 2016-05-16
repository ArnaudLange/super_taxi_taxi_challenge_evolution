package reseau;

import jeu.Joueur;

import java.io.IOException;
import java.net.*;
import java.util.List;

public class Serveur {

    public static void creerServeur(List listeJoueur)
    {

        ServerSocket listeningSocket;
        Socket socketClient = null;

        try
        {
            // Création du socket d'écoute
            listeningSocket = new ServerSocket(2009);
            System.out.println("Serveur à l'écoute sur le port " + listeningSocket.getLocalPort());

            long time = System.currentTimeMillis();//temps de depart
            long fin=time + 5000;//temps de fin
            long duree = (fin - time);
            System.out.println("durée = "+ duree/1000);

            boolean timeout = false;

            while(!timeout)
            {
                if (listeJoueur.size() >= 2)
                {
                    timeout = true;
                    System.out.println(" On a 2 joueurs ! ");
                    System.out.println(" On déclenche le timer");
                    listeningSocket.setSoTimeout(10000); // débloque le accept du socket
                }

                // Création du prochain joueur relié au client qui se connectera
                Joueur joueur = new Joueur();
                listeJoueur.add(joueur);

                try
                {
                    // Attente de la connexion à un client
                    socketClient = listeningSocket.accept();

                    // Lancement du thread qui gèrera le client avec l'objet Joueur en paramètre
                    Thread t = new Thread(new ConnexionClient(socketClient, joueur));
                    t.start();
                }
                catch (SocketTimeoutException e)
                {
                    System.out.println("Temps écoulé pour d'éventuels nouveaux joueurs. Début de la partie imminent");
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
