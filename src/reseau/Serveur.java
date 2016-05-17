package reseau;

import jeu.Joueur;

import java.io.IOException;
import java.net.*;
import java.util.List;

public class Serveur
{
    public static int TIMEOUT = 10000;

    public static void creerServeur(List listeJoueur, List listeConnexionClient)
    {

        ServerSocket listeningSocket;
        Socket socketClient = null;
        ConnexionClient connexionClient;

        try
        {
            // Création du socket d'écoute
            listeningSocket = new ServerSocket(2009);
            System.out.println("Serveur à l'écoute sur le port " + listeningSocket.getLocalPort());

            long startTimeOut = 0;//temps de depart

            boolean timeout = false;



            while(!timeout || (startTimeOut + TIMEOUT) < System.currentTimeMillis())
            {
                if (listeJoueur.size() >= 2)
                {

                    if (!timeout)
                        startTimeOut = System.currentTimeMillis();

                    listeningSocket.setSoTimeout(TIMEOUT);//(int)(startTimeOut + TIMEOUT - System.currentTimeMillis())); // débloque le accept du socket
                    timeout = true;
                    System.out.println(" On a 2 joueurs ! ");
                    System.out.println(" On déclenche le timer");

                    System.out.println("Current time : " + System.currentTimeMillis());
                    System.out.print("Timeout : " + (startTimeOut + TIMEOUT));

                }

                // Création du prochain joueur relié au client qui se connectera
                Joueur joueur = new Joueur();
                listeJoueur.add(joueur);

                try
                {
                    // Attente de la connexion à un client
                    socketClient = listeningSocket.accept();

                    // Lancement du thread qui gèrera le client avec l'objet Joueur en paramètre
                    connexionClient = new ConnexionClient(socketClient, joueur);
                    Thread t = new Thread(connexionClient);
                    t.start();
                    listeConnexionClient.add(connexionClient);
                }
                catch (SocketTimeoutException e)
                {
                    System.out.println("Temps écoulé pour d'éventuels nouveaux joueurs. Début de partie imminent");
                }

                System.out.println("Début de partie");
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
