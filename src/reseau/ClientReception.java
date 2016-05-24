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

    public ClientReception(InputStream in)
    {
        this.br = new BufferedReader(new InputStreamReader(in));
        gameOver = false;
        nbTour = 1;
    }

    public void run()
    {
        try
        {
            String messageEntrant = br.readLine(); // init buffer

            while(!gameOver){
                messageEntrant = br.readLine();


                System.out.println("Tour n°" + nbTour++);

                if (messageEntrant.equals(Action.GAMEOVER.toString()))
                {
                    System.out.println("Vous avez perdu");
                    gameOver = true;
                }
                if (messageEntrant.equals(Action.NEXT_ACTION.toString()))
                {
                    System.out.println("Prochaine action ? ");
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