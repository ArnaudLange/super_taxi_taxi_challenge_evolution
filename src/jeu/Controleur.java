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
        List<ConnexionClient> listeConnexionClientPerdu = new ArrayList();
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
        Joueur joueurActuel;

        while(!jeuFini)
        {
            // Tous les joueurs sont éliminés
            if(listJoueurs.size() <= 1)
            {
                jeuFini = true;
                break;
            }
            else
            {
                // Temps du tour atteint
                if (System.currentTimeMillis() - tempsDebutTour > tempsTour)
                {
                    tempsDebutTour = System.currentTimeMillis();
                    System.out.println("Tour " + nbTour++);
                    // les connexion clients se coupent
                    for (ConnexionClient c : listeConnexionClient)
                    {
                        joueurActuel = c.getJoueur();
                        // Gestion colision joueur
                        for (Joueur k : listJoueurs)
                        {
                            if (k == joueurActuel)
                                continue;
                            if (joueurActuel.getPosX() == k.getPosX() && joueurActuel.getPosY() == k.getPosY())
                            {
                                joueurActuel.setNbPoints(0);
                                c.envoyerMessage(Commande.GAMEOVER.toString());
                            }
                        }

                        // Gestion déconnexion et joueur perdu
                        if (!c.isAlive())
                        {
                            if (c.isGameOver())
                                System.out.println("Le joueur " + joueurActuel.getId() + " a perdu.");
                            else
                                System.out.println("Le joueur " + joueurActuel.getId() + " s'est déconnecté.");

                            listJoueurs.remove(joueurActuel);
                            nbJoueurOut++;
                            listeConnexionClientPerdu.add(c);
                            continue;
                        }

                        // Un joueur a gagné
                        if ((((c.getJoueur().getPosX() == jeu.getPosXObjectif()) && (joueurActuel.getPosY() == jeu.getPosYObjectif()))) || (listJoueurs.size() == 1))
                        {
                            jeu.setGagnant(joueurActuel);
                            jeuFini = true;
                            break;
                        }

                        // Envoie message joueur
                        if (joueurActuel.getNbPoints() != 0)
                        {
                            c.envoyerMessage("action");
                        }
                    }

                    // Suppression des clients déconnectés
                    for (ConnexionClient c : listeConnexionClientPerdu)
                        listeConnexionClient.remove(c);

                    listeConnexionClientPerdu.clear();
                }
            }
        }
    }
}