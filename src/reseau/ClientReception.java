package reseau;

import jeu.Commande;

import java.io.*;

/**
 * Created by swag on 25/05/16.
 */
public class ClientReception extends Thread
{
    private BufferedReader br;
    private boolean gameOver;
    private int nbTour;
    private String messageEntrant;

    public ClientReception(InputStream in)
    {
        this.br = new BufferedReader(new InputStreamReader(in));
        gameOver = false;
        nbTour = 0;
    }

    public void lireMessageEntrantBuffer()
    {
        try
        {
            if ((messageEntrant = br.readLine()) == null)
                Thread.currentThread().interrupt();
        }
        catch (IOException e)
        {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }

    public void run()
    {
        while((!gameOver) && !Thread.currentThread().isInterrupted())
        {
            lireMessageEntrantBuffer();

            switch (Commande.getCommande(messageEntrant))
            {
                case NOM:
                    System.out.println("Entrez votre nom :");
                    break;
                case GAMENOTWIN:
                    System.out.println("Vous avez perdu, un autre joueur a atteint l'objectif avant vous.");
                    gameOver = true;
                    break;
                case GAMEOVER:
                    System.out.println("Vous avez perdu");
                    gameOver = true;
                    break;
                case GAMEWIN:
                    System.out.println("Vous avez gagné");
                    gameOver = true;
                    break;
                case NEXT_ACTION:
                    System.out.println("Tour n°" + ++nbTour + "\nProchaine action ?");
                    break;
                case ERREUR_ACTION:
                    if (messageEntrant.equals(Commande.ERREUR_ACTION.toString()))
                        System.out.println("Mauvaise commande");
                    else
                        System.out.println(messageEntrant);
                    break;
                default:
                    System.out.println("Erreur de connexion avec le serveur");
            }
        }

        Thread.currentThread().interrupt();
    }
}