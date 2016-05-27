package reseau;

import carte.PointCardinal;
import jeu.Commande;
import jeu.Joueur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by swag on 14/05/16.
 */

public class ConnexionClient extends Thread
{

    private Socket socket;
    private Joueur joueur;
    private PrintWriter out;
    private BufferedReader in;
    private boolean changementVitesse;
    private String readLine;

    public ConnexionClient(Socket s, Joueur joueur) throws IOException
    {
        this.socket = s;
        this.joueur = joueur;
        this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        this.out = new PrintWriter(this.socket.getOutputStream());
        this.changementVitesse = false;
    }

    public void envoyerMessage(String s)
    {
        // Début de tour
        if (s.equals(Commande.NEXT_ACTION.toString()))
            this.changementVitesse = false;

        out.println(s);
        out.flush();

        // Si le client a perdu on stop le thread
        if (s.equals(Commande.GAMEOVER.toString()))
            Thread.currentThread().interrupt();
    }

    /**
     * Check si la connexion au client a été perdue en lisant la ligne
     * Interromp le thread si c'est le cas
     */
    public void lireLigneBuffer()
    {
        try
        {
            if ((readLine = in.readLine()) == null)
                Thread.currentThread().interrupt();
        }
        catch (IOException e)
        {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }

    public void run()
    {
        try
        {
            System.out.println("Le client numéro " + joueur.getId() + " est connecté !");

            // Traitement
            out.println(Commande.NOM.toString());
            out.flush();

            lireLigneBuffer();
            joueur.setNom(readLine);
            System.out.println("Nom joueur (côté serveur) : " + joueur.getNom());

            while(!Thread.currentThread().isInterrupted())
            {
                Thread.sleep(200);
                lireLigneBuffer();
                switch (Commande.getCommande(readLine))
                {
                    case NORD:
                        this.joueur.setDirection(PointCardinal.NORTH);
                        System.out.println("il va au nord");
                        break;
                    case SUD:
                        this.joueur.setDirection(PointCardinal.SOUTH);
                        break;
                    case EST:
                        this.joueur.setDirection(PointCardinal.EAST);
                        break;
                    case OUEST:
                        this.joueur.setDirection(PointCardinal.WEST);
                        break;
                    case ACCELERER:
                        if (!this.changementVitesse)
                        {
                            this.changementVitesse = true;
                            this.joueur.accelerer();
                            this.joueur.setDirection(null);
                        }
                        break;
                    case RALENTIR:
                        if (!this.changementVitesse)
                        {
                            this.changementVitesse = true;
                            this.joueur.ralentir();
                            this.joueur.setDirection(null);
                        }
                        break;
                    case AUCUNE_ACTION:
                        break;
                    default:
                        envoyerMessage(Commande.ERREUR_ACTION.toString());
                }
            }

        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        // On ferme le socket à la fin de la connexion
        try
        {
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