package reseau;

import jeu.Joueur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by swag on 14/05/16.
 */

public class ConnexionClient implements Runnable
{

    private ServerSocket listeningSocket;
    private Joueur joueur;
    private Socket socket;
    private PrintWriter out = null;
    private BufferedReader in = null;
    private int nbrclient = 1;

    public ConnexionClient(ServerSocket s, Joueur joueur)
    {
        listeningSocket = s;
    }

    public void run()
    {

        try {

            while(true)
            {
                socket = listeningSocket.accept(); // Un client se connecte on l'accepte
                System.out.println("Le client numéro "+nbrclient+ " est connecté !");
                nbrclient++;
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream());

                out.println("entrez votre nom :  ");
                out.flush();
                joueur.setNom(in.readLine());

                socket.close();
            }

        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}