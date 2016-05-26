package reseau;

import jeu.Action;

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
        nbTour = -1;
    }

    public void run()
    {
        try
        {
            while(!gameOver)
            {
                messageEntrant = br.readLine();

                if (nbTour++ > -1)
                    System.out.println("Tour n°" + nbTour);

                if (messageEntrant.equals(Action.GAMEOVER.toString()))
                {
                    System.out.println("Vous avez perdu");
                    gameOver = true;
                }
                else if (messageEntrant.equals(Action.NEXT_ACTION.toString()))
                {
                    System.out.println("Prochaine action ? ");
                }
                else
                {
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