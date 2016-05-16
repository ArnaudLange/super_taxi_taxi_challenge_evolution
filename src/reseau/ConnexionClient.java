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

    private Socket socket;
    private Joueur joueur;
    private PrintWriter out = null;
    private BufferedReader in = null;

    public ConnexionClient(Socket s, Joueur joueur)
    {
        this.socket = s;
        this.joueur = joueur;
    }

    public void run()
    {
        try
        {
            if (socket == null)
                System.out.println("socket null");
            System.out.println("Le client numéro " + joueur.getId() + " est connecté !");
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());

            // Traitement
            out.println("entrez votre nom :  ");
            out.flush();

            joueur.setNom(in.readLine());

            System.out.println("Nom joueur (côté serveur) : " + joueur.getNom());

            socket.close();

        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}