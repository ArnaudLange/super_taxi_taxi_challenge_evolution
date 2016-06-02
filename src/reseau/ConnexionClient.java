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
    private boolean premierTour;

    ConnexionClient(Socket s, Joueur joueur) throws IOException
    {
        this.socket = s;
        this.joueur = joueur;
        this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        this.out = new PrintWriter(this.socket.getOutputStream());
        this.gameOver = false;
        this.premierTour = true;
    }

    public void setGameOver(boolean b)
    {
        this.gameOver = b;
    }

    public void envoyerMessage(String s)
    {
        // Début de tour
        if (s.equals(Commande.NEXT_ACTION.toString()))
            derniereAction = null;

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

            // Demande du nom
            out.println(Commande.NOM.toString());
            out.flush();

            while(!gameOver && !Thread.currentThread().isInterrupted())
            {
                Thread.sleep(200);
                lireLigneBuffer();
                switch (Commande.getCommande(readLine))
                {
                    case DROITE:
                        changerDirection(Commande.DROITE);
                        break;
                    case GAUCHE:
                        changerDirection(Commande.GAUCHE);
                        break;
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
                        if (readLine.toLowerCase().startsWith("nom:"))
                        {
                            joueur.setNom(readLine.substring("nom:".length()));
                            envoyerMessage("Votre nom a bien été mis à jour : " + joueur.getNom());
                        }
                        else
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

    private void undoDroiteGauche(Commande d){
        if(Commande.GAUCHE.equals(d)) {
            changerDirection(Commande.DROITE);
        }else{
            changerDirection(Commande.GAUCHE);
        }
        envoyerMessage("Annulation du changement de direction : direction = " + joueur.getDirection());
    }

    private boolean actionValide(Commande d){
        if(Commande.NORD.equals(d) || Commande.EST.equals(d) || Commande.SUD.equals(d) || Commande.OUEST.equals(d)){
            if(joueur.getDirection()==null){
                return true;
            }else if(Commande.NORD.equals(derniereAction) || Commande.EST.equals(derniereAction) || Commande.SUD.equals(derniereAction) || Commande.OUEST.equals(derniereAction)){
                return true;
            }else{
                return false;
            }
        }else{
            if(joueur.getDirection()==null) {
                if (Commande.GAUCHE.equals(d) || Commande.DROITE.equals(d)) {
                    return false;
                }
            }else{
                if (Commande.GAUCHE.equals(d) || Commande.DROITE.equals(d)) {
                    return true;
                }
            }
        }
        return false;
    }

    private void changerDirection(Commande d)
    {
        //envoyerMessage("DEBUT \n COMMANDE = "+d);

        PointCardinal nouvelleDirection;

        boolean actionValide = actionValide(d);
        //envoyerMessage("ACTION VALIDE ? "+actionValide);
        if(!actionValide){
            //envoyerMessage("Commande invalide");
            return;
        }

        //envoyerMessage("DOIT ON ANNULER DERNIER ACTION ?"+(derniereAction != null));
        if(derniereAction != null){
            anullerDerniereAction(derniereAction);
        }


        if (Commande.NORD.equals(d) || Commande.EST.equals(d) || Commande.SUD.equals(d) || Commande.OUEST.equals(d)) {
            nouvelleDirection=PointCardinal.getPointCardinal(d.toString());
            //envoyerMessage("NOUVELLE DIRECTION (nord/sud/est/ouest) = "+d.toString());
        }else{

            derniereDirection = joueur.getDirection();
            switch(derniereDirection){
                case NORD:
                    if (Commande.DROITE.equals(d)) {
                        nouvelleDirection = PointCardinal.EST;
                    } else if (Commande.GAUCHE.equals(d)) {
                        nouvelleDirection = PointCardinal.OUEST;
                    }else{
                        //envoyerMessage("RETURN NORD");
                        return;
                    }
                    break;
                case EST:
                    if (Commande.DROITE.equals(d)) {
                        nouvelleDirection = PointCardinal.SUD;
                    } else if (Commande.GAUCHE.equals(d)) {
                        nouvelleDirection = PointCardinal.NORD;
                    }else{
                        //envoyerMessage("RETURN EST");
                        return;
                    }
                    break;
                case SUD:
                    if (Commande.DROITE.equals(d)) {
                        nouvelleDirection = PointCardinal.OUEST;
                    } else if (Commande.GAUCHE.equals(d)) {
                        nouvelleDirection = PointCardinal.EST;
                    }else{
                        //envoyerMessage("RETURN SUD");
                        return;
                    }
                    break;
                case OUEST:
                    if (Commande.DROITE.equals(d)) {
                        nouvelleDirection = PointCardinal.NORD;
                    } else if (Commande.GAUCHE.equals(d)) {
                        nouvelleDirection = PointCardinal.SUD;
                    }else{
                        //envoyerMessage("RETURN OUEST");
                        return;
                    }
                    break;
                default:
                    //envoyerMessage("RETURN DEFAULT");
                    return;
            }
        }
        //envoyerMessage("SUCCESS NOUVELLE DIRECTION");
        joueur.setDirection(nouvelleDirection);
        derniereAction=d;
        //envoyerMessage("FIN \n NOUVELLE DIRECTION = "+nouvelleDirection);
        //envoyerMessage("DERNIERE ACTION = "+derniereAction);
        envoyerMessage("Vous avez changé votre direction vers " + nouvelleDirection.toString());
    }

    private void anullerDerniereAction(Commande action){
        Commande temp;
        if (Commande.ACCELERER.equals(derniereAction)) {
            joueur.ralentir();
            derniereAction=null;
            envoyerMessage("Annulation du changement de vitesse : vitesse = " + joueur.getVitesse());
        } else if (Commande.RALENTIR.equals(derniereAction)) {
            joueur.accelerer();
            derniereAction=null;
            envoyerMessage("Annulation du changement de vitesse : vitesse = " + joueur.getVitesse());
        }else if(Commande.NORD.equals(derniereAction)||Commande.EST.equals(derniereAction)||Commande.SUD.equals(derniereAction)||Commande.OUEST.equals(derniereAction)){
            derniereAction=null;
            joueur.setDirection(null);

        }else if(Commande.DROITE.equals(derniereAction)){
            temp=derniereAction;
            derniereAction=null;
            undoDroiteGauche(temp);
        }else if(Commande.GAUCHE.equals(derniereAction)){
            temp=derniereAction;
            derniereAction=null;
            undoDroiteGauche(temp);
        }
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
                envoyerMessage("je suis la"+derniereAction+"on va mettre ca"+derniereDirection);
                envoyerMessage("Annulation du changement de direction : direction = " + derniereDirection);
                joueur.setDirection(derniereDirection);
                derniereAction=null;
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
            }
            else if (c.equals(Commande.RALENTIR))
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