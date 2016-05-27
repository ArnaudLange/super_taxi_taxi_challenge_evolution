package reseau;

import jeu.Commande;
import java.io.*;
import java.util.Scanner;

/**
 * Created by swag on 25/05/16.
 */
public class ClientEnvoie extends Thread
{

    private Scanner in;
    private PrintWriter out;
    private int nbTour;
    private Commande action;

    public ClientEnvoie(OutputStream out)
    {
        this.out = new PrintWriter(out);
        nbTour = 1;
        in = new Scanner(System.in);
    }

    public void run()
    {
        out.println(in.nextLine());
        out.flush();

        while(!Thread.currentThread().isInterrupted())
        {
            action = Commande.getCommande(in.nextLine());
            out.println(action);
            out.flush();
        }
    }
}

