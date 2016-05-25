package jeu;

import carte.Carte;
import carte.InterpreteurCarte;
import reseau.ConnexionClient;
import reseau.Serveur;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Created by jimmy on 14/05/16.
 */
public class Controleur
{

    public static void main(String[] args)
    {
        File fichierCarte = new File("src/carte/carte001.txt"); // on initialise le fichier texte de la carte
        Carte carte = InterpreteurCarte.Interpreter(fichierCarte);

        List<Joueur> listJoueurs = new ArrayList<>();
        Jeu jeu = new Jeu(listJoueurs, carte);
        List<ConnexionClient> listeConnexionClient = new ArrayList();
        Serveur.creerServeur(jeu.getListeJoueurs(), listeConnexionClient);
        Vector positionDepart = InterpreteurCarte.trouverPositionDepart(carte);

        //System.out.println("Nom joueur 1 : "+ listJoueurs.get(0).getNom());
        //System.out.println("Nom joueur 2 : "+ listJoueurs.get(1).getNom());

        int i = 1;
        for (Joueur joueur : listJoueurs)
        {
            int[] position = InterpreteurCarte.choisirPositionDepart(positionDepart);
            int posX = position[0];
            int posY = position[1];
            System.out.println("La position en X du joueur "+ i + " est " + posX);
            System.out.println("La position en Y du joueur "+ i + " est " + posY);
            joueur.setPosX(posX);
            joueur.setPosY(posY);
            i++;
        }

        int nbTour=1;
        int nbJoueurOut = 0;
        boolean jeuFini = false;
        long tempsTour = 5000;
        long tempsDebutTour = System.currentTimeMillis();

        while(!jeuFini)
        {
            // Temps du tour atteint
            if (System.currentTimeMillis() - tempsDebutTour > tempsTour)
            {
                tempsDebutTour = System.currentTimeMillis();
                System.out.println("Tour " + nbTour++);

                for (ConnexionClient c : listeConnexionClient)
                {
                    if (c.getJoueur().getNbPoints() == 0)
                    {
                        nbJoueurOut++;
                        continue;
                    }

                    // Un joueur a gagné
                    if (((c.getJoueur().getPosX() == jeu.getPosXObjectif()) && (c.getJoueur().getPosY() == jeu.getPosYObjectif())))
                    {
                        jeu.setGagnant(c.getJoueur());
                        jeuFini = true;
                        break;
                    }

                    // Tous les joueurs sont éliminés
                    if(nbJoueurOut == listJoueurs.size())
                    {
                        jeuFini = true;
                        break;
                    }

                    /*for (Joueur k : listJoueurs)
                        if(c.getJoueur().getPosX() == k.getPosX() && c.getJoueur().getPosY() == k.getPosY())
                            c.getJoueur().setNbPoints(0); // TODO envoyer aux clients colisions
                    */
                    nbJoueurOut = 0;
                    c.envoyerMessage("action");//TODO Gérer les actions a chaque tour
                }
            }
        }
    }
}