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
    private String readLine;
    private Commande dernierChangementVitesse;
    private int nbTour;
    private boolean gameOver;

    ConnexionClient(Socket s, Joueur joueur) throws IOException
    {
        this.socket = s;
        this.joueur = joueur;
        this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        this.out = new PrintWriter(this.socket.getOutputStream());
        this.gameOver = false;
        this.nbTour = 0;
    }

    public void setGameOver(boolean b)
    {
        this.gameOver = b;
    }

    public void envoyerMessage(String s)
    {
        // Début de tour
        if (s.equals(Commande.NEXT_ACTION.toString()))
        {
            this.dernierChangementVitesse = null;
            this.nbTour++;
        }

        out.println(s);
        out.flush();
    }

    /**
     * Check si la connexion au client a été perdue en lisant la ligne
     * Interromp le thread si c'est le cas
     */
    private void lireLigneBuffer()
    {
        try
        {
            if ((readLine = in.readLine()) == null)
                Thread.currentThread().interrupt();
        }
        catch (IOException e)
        {
            Thread.currentThread().interrupt();
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

            while(!gameOver && !Thread.currentThread().isInterrupted())
            {
                Thread.sleep(200);
                lireLigneBuffer();
                switch (Commande.getCommande(readLine))
                {
                    case NORD:
                        changerDirection(PointCardinal.NORTH);
                        break;
                    case SUD:
                        changerDirection(PointCardinal.SOUTH);
                        break;
                    case EST:
                        changerDirection(PointCardinal.EAST);
                        break;
                    case OUEST:
                        changerDirection(PointCardinal.WEST);
                        break;
                    case ACCELERER:
                        changerVitesse(Commande.ACCELERER);
                        break;
                    case RALENTIR:
                        changerVitesse(Commande.RALENTIR);
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
            System.err.println("Erreur de connexion avec le client");
        }

        // On ferme le socket à la fin de la connexion
        try
        {
            socket.close();
        } catch (IOException e)
        {
            System.err.println("Erreur socket déjà fermé");
        }
    }

    private void changerDirection(PointCardinal d)
    {
        if(dernierChangementVitesse != null)
        {
            changerVitesse(dernierChangementVitesse);
            dernierChangementVitesse = null;
        }
        joueur.setDirection(d);
    }

    private void changerVitesse(Commande c)
    {
        if (nbTour == 1)
            joueur.setDirection(null);

        if (c.equals(Commande.ACCELERER))
        {
            joueur.accelerer();
            dernierChangementVitesse = Commande.ACCELERER;
        }
        else if (c.equals(Commande.RALENTIR))
        {
            joueur.ralentir();
            dernierChangementVitesse = Commande.RALENTIR;
        }
    }

    public Joueur getJoueur()
    {
        return joueur;
    }
}