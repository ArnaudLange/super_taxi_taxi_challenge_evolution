package reseau;

import jeu.Action;
import java.io.*;
import java.util.Scanner;

/**
 * Created by swag on 25/05/16.
 */
public class ClientEnvoie{

    private Scanner in;
    private String nom;
    private PrintWriter out;
    private PrintStream pS;
    private int nbTour;
    private Action action;

    public ClientEnvoie(PrintWriter out, PrintStream pS)
    {
        this.out = out;
        this.pS = pS ;
        nbTour = 1;
        in = new Scanner(System.in);
        System.setOut(pS);
    }

    public void print (String s)
    {
        System.out.print(s);
    }

    public void run()
    {
        System.out.println("test");
        out.println(in.nextLine());
        out.flush();

        /*while(!Thread.currentThread().isInterrupted())
        {
                action = Action.getAction(in.nextLine());
                out.println(action);
                out.flush();
        }*/
        quit();
    }

    public void quit()
    {
        Thread.currentThread().interrupt();
    }
}

