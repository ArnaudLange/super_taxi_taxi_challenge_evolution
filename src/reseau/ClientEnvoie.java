package reseau;

import jeu.Commande;
import java.io.*;
import java.util.Scanner;

/**
 * Created by swag on 25/05/16.
 */
public class ClientEnvoie extends Thread
{
    private BufferedReader in;
    private PrintWriter pW;
    private int nbTour;
    private Commande action;

    public ClientEnvoie(OutputStream out)
    {
        this.pW = new PrintWriter(out);
        nbTour = 1;
        InputStreamReader isr = new InputStreamReader(System.in);
        in = new BufferedReader(isr);
    }

    public void run()
    {
        try
        {
            pW.println(in.readLine());
            pW.flush();

            while(!Thread.currentThread().isInterrupted())
            {
                action = Commande.getCommande(in.readLine());
                pW.println(action);
                pW.flush();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void close()
    {
        System.exit(0);
    }
}

