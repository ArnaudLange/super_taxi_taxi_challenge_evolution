package reseau;

import carte.Case;
import carte.PointCardinal;
import com.sun.org.apache.xpath.internal.SourceTree;
import jeu.Commande;
import jeu.Constante;

import javax.swing.text.Position;
import java.io.*;

/**
 * Created by swag on 25/05/16.
 */
public class ClientReception extends Thread
{
    private String vision;
    private BufferedReader br;
    private boolean gameOver;
    private int nbTour;
    private String messageEntrant;

    private int posX;
    private int posY;
    private int posXObjectif;
    private int posYObjectif;
    private PointCardinal direction;
    private int vitesse;
    private int nbPoint;
    //private Case[][] vision;

    public ClientReception(InputStream in)
    {
        this.br = new BufferedReader(new InputStreamReader(in));
        gameOver = false;
        nbTour = 0;
    }

    public void lireMessageEntrantBuffer()
    {
        try
        {
            if ((messageEntrant = br.readLine()) == null)
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
        while((!gameOver) && !Thread.currentThread().isInterrupted())
        {
            lireMessageEntrantBuffer();

            switch (Commande.getCommande(messageEntrant))
            {
                case NOM:
                    System.out.println("Pour spécifier votre nom, tapez \"Nom:\" suivi de votre nom (sans espace)");
                    break;
                case GAMENOTWIN:
                    System.out.println("Vous avez perdu, un autre joueur a atteint le score maximum avant vous.");
                    gameOver = true;
                    break;
                case GAMEOVER:
                    System.out.println("Vous avez perdu");
                    gameOver = true;
                    break;
                case GAMEWINOBJ:
                    System.out.println("Vous avez gagné car vous avez obtenu "+ Constante.MAXPOINTS+ " points ! ");
                    gameOver = true;
                    System.out.println(gameOver);
                    break;
                case GAMEWINLASTPLAYER:
                    System.out.println("Vous avez gagné car vous êtes le dernier joueur en vie ! ");
                    gameOver = true;
                    break;
                case NEXT_ACTION:
                    System.out.println("-------------------------------------");
                    System.out.println("\tTour n°" + ++nbTour);
                    System.out.println(vision);
                    System.out.println("Position objectif : "+posXObjectif+", "+posYObjectif);
                    System.out.println("Position en X : "+posX);
                    System.out.println("Position en Y : "+posY);
                    System.out.println("Points : "+nbPoint);
                    System.out.println("Vitesse : "+ vitesse);
                    System.out.println("Direction : "+ direction);
                    System.out.println("Prochaine action ? ( accelerer, ralentir, droite, gauche )");

                    break;
                case ERREUR_ACTION:
                    if (messageEntrant.equals(Commande.ERREUR_ACTION.toString()))
                        System.err.println("Mauvaise commande");
                    else {
                        String tmp[];
                        if (messageEntrant.startsWith("pos:")) {
                            tmp = messageEntrant.substring("pos:".length()).split(",");
                            posX = Integer.parseInt(tmp[0]);
                            posY = Integer.parseInt(tmp[1]);
                        } else if (messageEntrant.startsWith("dir:"))
                            direction = PointCardinal.getPointCardinal(messageEntrant.substring("dir:".length()));

                        else if (messageEntrant.startsWith("vit:"))
                            vitesse = Integer.parseInt(messageEntrant.substring("vit:".length()));

                        else if (messageEntrant.startsWith("nbp:"))
                            nbPoint = Integer.parseInt(messageEntrant.substring("nbp:".length()));

                        else if (messageEntrant.startsWith("carte:")) {
                            String carte;
                            carte=messageEntrant.substring("carte:".length());
                            int i;
                            int nbLignes,nbColonnes;
                            nbLignes=0;
                            nbColonnes=0;
                            vision = "";
                            for (i = 0; i < carte.length(); i++) {
                                if (carte.charAt(i)==';') {
                                    vision = vision + "\n";
                                    nbLignes++;
                                    nbColonnes=0;
                                }else if(carte.charAt(i)=='X'){
                                    posXObjectif=nbColonnes;
                                    posYObjectif=nbLignes;
                                    vision = vision + carte.charAt(i);
                                } else {
                                    if(carte.charAt(i)==','){
                                        nbColonnes++;
                                    }
                                    vision = vision + carte.charAt(i);
                                }
                            }

                            } else {
                                System.out.println(messageEntrant);
                            }
                        }
                        break;
                default:

                    System.err.println("Erreur de connexion avec le serveur");


            }
        }

        Thread.currentThread().interrupt();
    }
}