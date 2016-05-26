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

public class ConnexionClient implements Runnable
{

    private Socket socket;
    private Joueur joueur;
    private PrintWriter out;
    private BufferedReader in;
    private boolean changementVitesse;

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

        // Si le client a perdu on ferme la connexion et on stop le thread
        if (s.equals(Commande.GAMEOVER.toString()))
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
            out.println(Commande.NOM.toString());
            out.flush();

            joueur.setNom(in.readLine());
            System.out.println("Nom joueur (côté serveur) : " + joueur.getNom());

            while(true) //TODO Impossible d'envoyer un message au client avec out.println ?!?§
            // Il faut verouiller l'envoi du message si la personne a envoyer une action
            // et lui envoyer un message "mauvaise commande" si il tape une mauvaise commande
            // dès que le client envoie une action, le tour suivant s'affiche chez lui
            {
                Thread.sleep(200);
                switch (Commande.getAction(in.readLine()))
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