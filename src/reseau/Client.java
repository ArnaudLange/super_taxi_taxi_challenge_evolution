package reseau;

import jeu.Action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.util.Scanner;

public class Client {

    private BufferedReader br;
    private String nom;
    private Action action;
    private boolean gameOver;
    private Socket socket;
    private int nbTour;
    private ClientEnvoie cE;

    public Client()
    {
        gameOver = false;
        nbTour = 1;
        try {
            socket = new Socket("localhost", 2009);
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            cE = new ClientEnvoie(new PrintWriter(socket.getOutputStream()), System.out);
            cE.run();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            quit();
        }

    }

    public void run()
    {
        try
        {
            cE.print("Nom ?");

            String messageEntrant = br.readLine(); // init buffer

            while(!gameOver){
                messageEntrant = br.readLine();


                cE.print("Tour n°" + nbTour++);

                if (messageEntrant.equals(Action.GAMEOVER.toString()))
                {
                    cE.print("Vous avez perdu");
                    gameOver = true;
                }
                if (messageEntrant.equals(Action.NEXT_ACTION.toString()))
                {
                    cE.print("Prochaine action ? ");
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
        cE.quit();
        try {
            socket.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        Thread.currentThread().interrupt();
    }
}

