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

    public void run()
    {
        try
        {
            while(!gameOver)
            {
                messageEntrant = br.readLine();

                switch (Commande.getAction(messageEntrant))
                {
                    case NOM:
                        System.out.println("Entrez votre nom :");
                        break;
                    case GAMEOVER:
                        System.out.println("Vous avez perdu");
                        gameOver = true;
                        break;
                    case NEXT_ACTION:
                        System.out.println("Tour n°" + ++nbTour + "\nProchaine action ? ");
                        break;
                    case ERREUR_ACTION:
                        System.out.println("Mauvaise commande");
                        break;
                    default:
                        System.out.println(messageEntrant);
                }
            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        quit();
    }

    private void quit()
    {
        Thread.currentThread().interrupt();
    }
}