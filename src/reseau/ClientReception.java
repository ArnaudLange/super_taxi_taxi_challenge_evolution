package reseau;

import carte.PointCardinal;
import jeu.Commande;
import jeu.Constante;

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

    private int posX;
    private int posY;
    private PointCardinal direction;
    private int vitesse;
    private int nbPoint;

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
                    System.out.println("Pour spécifier votre nom, tapez \"Nom:\" suivi de votre nom (sans espace)");
                    break;
                case GAMENOTWIN:
                    System.out.println("Vous avez perdu, un autre joueur a atteint l'objectif avant vous.");
                    gameOver = true;
                    break;
                case GAMEOVER:
                    System.out.println("Vous avez perdu");
                    gameOver = true;
                    break;
                case GAMEWINOBJ:
                    System.out.println("Vous avez gagné car vous avez obtenu "+ Constante.MAXPOINTS+ "points ! ");
                    gameOver = true;
                    break;
                case GAMEWINLASTPLAYER:
                    System.out.println("Vous avez gagné car vous êtes le dernier joueur en vie ! ");
                    gameOver = true;
                    break;
                case NEXT_ACTION:
                    System.out.println("Tour n°" + ++nbTour + "\nProchaine action ?");
                    break;
                case ERREUR_ACTION:
                    if (messageEntrant.equals(Commande.ERREUR_ACTION.toString()))
                        System.err.println("Mauvaise commande");
                    else
                    {
                        String tmp[];

                        if (messageEntrant.startsWith("pos:"))
                        {
                            tmp = messageEntrant.substring("pos:".length()).split(",");
                            posX = Integer.parseInt(tmp[0]);
                            posY = Integer.parseInt(tmp[1]);
                        }
                        else if (messageEntrant.startsWith("dir:"))
                            direction = PointCardinal.getPointCardinal(messageEntrant.substring("dir:".length()));

                        else if (messageEntrant.startsWith("vit:"))
                            vitesse = Integer.parseInt(messageEntrant.substring("vit:".length()));

                        else if (messageEntrant.startsWith("nbp:"))
                            nbPoint = Integer.parseInt(messageEntrant.substring("nbp:".length()));

                        else
                            System.out.println(messageEntrant);
                    }
                    break;
                default:
                    System.err.println("Erreur de connexion avec le serveur");
            }
        }

        Thread.currentThread().interrupt();
    }
}