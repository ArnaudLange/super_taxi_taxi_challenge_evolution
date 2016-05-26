package reseau;

import carte.PointCardinal;
import jeu.Action;
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

        // Si le client a perdu on ferme la connexion et on stop le thread
        if (s.equals(Action.GAMEOVER.toString()))
        {
            try
            {
            socket.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
            Thread.currentThread().interrupt();
        }
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

            while(true) //TODO Impossible d'envoyer un message au client avec out.println ?!?§
            // Il faut verouiller l'envoi du message si la personne a envoyer une action
            // et lui envoyer un message "mauvaise commande" si il tape une mauvaise commande
            // dès que le client envoie une action, le tour suivant s'affiche chez lui
            {
                Thread.sleep(200);
                switch (Action.getAction(in.readLine()))
                {
                    case NORD:
                        this.joueur.setDirection(PointCardinal.NORTH);
                        System.out.println("il va au nord");
                    case SUD:
                        this.joueur.setDirection(PointCardinal.SOUTH);
                    case EST:
                        this.joueur.setDirection(PointCardinal.EAST);
                    case OUEST:
                        this.joueur.setDirection(PointCardinal.WEST);
                    case ACCELERER:
                        this.joueur.accelerer();
                    case RALENTIR:
                        this.joueur.ralentir();
                    default:
                }
            }

        } catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    public Joueur getJoueur()
    {
        return joueur;
    }
}