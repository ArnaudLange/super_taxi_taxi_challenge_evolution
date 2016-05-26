package reseau;

import jeu.Joueur;

import java.io.IOException;
import java.net.*;
import java.util.List;

import static java.lang.Thread.sleep;

public class Serveur
{
    public static int TIMEOUT = 10000;

    public static void creerServeur(List<Joueur> listJoueurs, List listeConnexionClient)
    {

        ServerSocket listeningSocket;
        Socket socketClient = null;
        ConnexionClient connexionClient;

        try
        {
            // Création du socket d'écoute
            listeningSocket = new ServerSocket(2009);
            System.out.println("Serveur à l'écoute sur le port " + listeningSocket.getLocalPort());

            long startTimeOut = System.currentTimeMillis();//temps de depart

            boolean timeout = false;


            while(!timeout || (System.currentTimeMillis() - startTimeOut < TIMEOUT))
            {
                int taille = listJoueurs.size();

                //Pour éviter que le timer ne débute alors que les noms des joueurs
                //ne sont pas encore été définis par les ConnexionClient, on met le code suivant
                //qui va attendre trois secondes afin d'être sur que le nom a bien été initialisé

                /*if (taille > 0)
                    if (listJoueurs.isEmpty())
                        sleep(3000);*/

                if (taille >= 2) //TODO Gérer le cas ou le client 2 disconnect avant le début de la partie
                {
                    if (!timeout)
                        startTimeOut = System.currentTimeMillis();

                    // débloque le accept du socket
                    listeningSocket.setSoTimeout(TIMEOUT - (int)(System.currentTimeMillis() - startTimeOut));
                    timeout = true;
                }

                // Création du prochain joueur relié au client qui se connectera
                Joueur joueur = new Joueur();

                try
                {
                    // Attente de la connexion à un client
                    socketClient = listeningSocket.accept();

                    // Lancement du thread qui gèrera le client avec l'objet Joueur en paramètre
                    connexionClient = new ConnexionClient(socketClient, joueur);
                    Thread t = new Thread(connexionClient);
                    t.start();
                    listJoueurs.add(joueur);
                    listeConnexionClient.add(connexionClient);
                }
                catch (SocketTimeoutException e)
                {
                    System.out.println("Temps écoulé pour d'éventuels nouveaux joueurs. Début de partie imminent");
                }
            }
        }
        catch (IOException e)
        {
            System.out.println("caca");
            e.printStackTrace();
        }
    }
}
