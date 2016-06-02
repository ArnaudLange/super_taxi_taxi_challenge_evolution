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
    private Commande derniereAction;
    private PointCardinal derniereDirection;
    private boolean gameOver;

    ConnexionClient(Socket s, Joueur joueur) throws IOException
    {
        this.socket = s;
        this.joueur = joueur;
        this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        this.out = new PrintWriter(this.socket.getOutputStream());
        this.gameOver = false;
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
            derniereAction = null;
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
            envoyerMessage("Connexion avec le serveur établie");

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
                        changerDirection(Commande.NORD);
                        break;
                    case SUD:
                        changerDirection(Commande.SUD);
                        break;
                    case EST:
                        changerDirection(Commande.EST);
                        break;
                    case OUEST:
                        changerDirection(Commande.OUEST);
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

    private void changerDirection(Commande d)
    {
        envoyerMessage("Vous avez changé votre direction vers " + d.toString());

        if (Commande.ACCELERER.equals(derniereAction))
        {
            joueur.ralentir();
            envoyerMessage("Annulation du changement de vitesse : vitesse = " + joueur.getVitesse());
        }
        else if (Commande.RALENTIR.equals(derniereAction))
        {
            joueur.accelerer();
            envoyerMessage("Annulation du changement de vitesse : vitesse = " + joueur.getVitesse());
        }

        derniereAction = d;
        derniereDirection= joueur.getDirection();
        joueur.setDirection(PointCardinal.getPointCardinal(d.toString()));
    }

    private void changerVitesse(Commande c)
    {
        if (joueur.getDirection() == null)
            envoyerMessage("Vous devez d'abord choisir une direction avant de changer votre vitesse");
        else if (derniereAction != null && PointCardinal.getPointCardinal(derniereAction.toString()) != null && derniereDirection == null)
        {
            envoyerMessage("Impossible de changer de vitesse et de direction dans le même tour");
        }
        else
        {
            // S'il a déjà changer de direction on remets le joueur dans sa derniere direction possible
            if (derniereAction != null && PointCardinal.getPointCardinal(derniereAction.toString()) != null)
            {
                envoyerMessage("Annulation du changement de direction : direction = " + derniereDirection);
                joueur.setDirection(derniereDirection);
            }

            if (c.equals(Commande.ACCELERER))
            {
                if (Commande.RALENTIR.equals(derniereAction))
                {
                    joueur.accelerer();
                    joueur.accelerer();
                    derniereAction = Commande.ACCELERER;
                    envoyerMessage("Annulation du changement de vitesse et accélération : vitesse = " + joueur.getVitesse());
                }
                else if (Commande.ACCELERER.equals(derniereAction))
                {
                    envoyerMessage("Vous avez déjà accélerer à pendant ce tour");
                }
                else
                {
                    joueur.accelerer();
                    derniereAction = Commande.ACCELERER;
                    envoyerMessage("Vous accélerez : vitesse = " + joueur.getVitesse());
                }
            } else if (c.equals(Commande.RALENTIR))
            {
                if (Commande.ACCELERER.equals(derniereAction))
                {
                    joueur.ralentir();
                    joueur.ralentir();
                    derniereAction = Commande.RALENTIR;
                    envoyerMessage("Annulation du changement de vitesse et ralentissement : vitesse = " + joueur.getVitesse());
                }
                else if (Commande.RALENTIR.equals(derniereAction))
                {
                    envoyerMessage("Vous avez déjà ralenti à pendant ce tour");
                }
                else
                {
                    joueur.ralentir();
                    derniereAction = Commande.RALENTIR;
                    envoyerMessage("Vous ralentissez : vitesse = " + joueur.getVitesse());
                }
            }
        }
    }

    public Joueur getJoueur()
    {
        return joueur;
    }
}