package reseau;

import jeu.Action;
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
    private Action action;

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
                action = Action.getAction(in.nextLine());
                out.println(action);
                out.flush();
        }
        quit();
    }

    public void quit()
    {
        Thread.currentThread().interrupt();
    }
}

