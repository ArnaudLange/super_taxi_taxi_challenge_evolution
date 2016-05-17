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

    public ConnexionClient(Socket s, Joueur joueur) throws IOException {
        this.socket = s;
        this.joueur = joueur;
        this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        this.out = new PrintWriter(this.socket.getOutputStream());
    }

    public void envoyerMessage(String s)
    {
        out.println(s);
        out.flush();
    }


    public void run()
    {
        try
        {
            System.out.println("Le client numéro " + joueur.getId() + " est connecté !");

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

    public Joueur getJoueur()
    {
        return joueur;
    }
}