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


            Joueur joueur = new Joueur("", 10, 0, true, null);
            listeJoueur.add(joueur);


            Thread t = new Thread(new ConnexionClient(listeningSocket, joueur));
            t.start();
            System.out.println("Serveur à l'écoute sur le port " + listeningSocket.getLocalPort());


        } catch (IOException e)
        {

            e.printStackTrace();
        }
    }
}
