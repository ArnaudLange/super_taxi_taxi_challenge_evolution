package reseau;

import jeu.Action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.util.Scanner;

public class Client {

    private PrintWriter out = null;
    private BufferedReader in = null;
    private Scanner sc = null;
    private String nom;
    private Action action;

    public void run()
    {

        Socket socket;

        try
        {

            socket = new Socket("localhost",2009);
            out = new PrintWriter(socket.getOutputStream()); //
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            sc = new Scanner(System.in);

            System.out.println(in.readLine());
            nom = sc.nextLine();

            out.println(nom);
            out.flush();

        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    public void envoyerAction(){
        System.out.println("Prochaine action ? ");
        action = Action.getAction(sc.nextLine());
        out.println(action);
        out.flush();
    }
}
