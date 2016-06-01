package jeu;

import carte.Carte;
import carte.Infraction;
import carte.InterpreteurCarte;
import reseau.ConnexionClient;
import reseau.Serveur;

import java.io.File;
import java.util.*;

/**
 * Created by jimmy on 14/05/16.
 */
public class Controleur implements Observer {

    private Jeu jeu;

    public Controleur() {
        File fichierCarte = new File("src/carte/carte001.txt"); // on initialise le fichier texte de la carte
        Carte carte = InterpreteurCarte.Interpreter(fichierCarte);



        /*carte.addObserver(this);

        Joueur j = new Joueur();
        j.setPosX(0);
        j.setPosY(0);
        j.setVitesse(4);
        j.setDirection(PointCardinal.EAST);

        List<Joueur> listos = new ArrayList<Joueur>();
        listos.add(j);

        jeu = new Jeu(listos, carte);
        jeu.getCarte().gestionDeplacements(j);*/


        List<Joueur> listJoueurs = new ArrayList<>();
        Jeu jeu = new Jeu(listJoueurs, carte);
        List<ConnexionClient> listeConnexionClient = new ArrayList();
        List<ConnexionClient> listeConnexionClientPerdu = new ArrayList();
        Serveur.creerServeur(jeu.getListeJoueurs(), listeConnexionClient);
        Vector positionDepart = InterpreteurCarte.trouverPositionDepart(carte);

        int i = 1;
        for (Joueur joueur : listJoueurs)
        {
            int[] position = InterpreteurCarte.choisirPositionDepart(positionDepart);
            int posX = position[0];
            int posY = position[1];
            joueur.setPosX(posX);
            joueur.setPosY(posY);
            System.out.println("La position en X du joueur "+ i + " est " + posX);
            System.out.println("La position en Y du joueur "+ i + " est " + posY);
            i++;
        }


        int nbTour=1;
        boolean jeuFini = false;
        long tempsTour = 2000;
        long tempsDebutTour = System.currentTimeMillis();
        Joueur joueurActuel;

        while(!jeuFini)
        {
            // Temps du tour atteint
            if (System.currentTimeMillis() - tempsDebutTour > tempsTour)
            {
                System.out.println("Tour " + nbTour++);
                tempsDebutTour = System.currentTimeMillis();

                // Boucle sur la liste des clients connectés
                for (ConnexionClient c : listeConnexionClient)
                {
                    joueurActuel = c.getJoueur();

                    // Gestion déconnexion
                    if (!c.isAlive())
                    {
                        System.out.println("Le joueur " + joueurActuel.getId() + " s'est déconnecté.");
                        listJoueurs.remove(joueurActuel);
                        listeConnexionClientPerdu.add(c);
                        continue;
                    }

                    // Gestion colision joueur
                    for (Joueur k : listJoueurs)
                    {
                        if (k == joueurActuel || k.getNbPoints() == 0)
                            continue;
                        if (joueurActuel.getPosX() == k.getPosX() && joueurActuel.getPosY() == k.getPosY())
                        {
                            System.out.println("Colision entre le joueur " + joueurActuel.getId() + " et le joueur " + k.getId());
                            System.out.println("Le joueur " + joueurActuel.getId() + " a perdu.");
                            System.out.println("Le joueur " + k.getId() + " a perdu.");

                            joueurActuel.setNbPoints(0);
                            k.setNbPoints(0);
                        }
                    }

                    // Un joueur a gagné
                    if ((((joueurActuel.getPosX() == jeu.getPosXObjectif()) && (joueurActuel.getPosY() == jeu.getPosYObjectif()))))
                    {
                        System.out.println("Objectif atteint !");
                        jeu.setGagnant(joueurActuel);
                        jeuFini = true;

                        for (ConnexionClient cC : listeConnexionClient)
                        {
                            if (jeu.getGagnant().equals(cC.getJoueur()))
                                cC.envoyerMessage(Commande.GAMEWIN.toString());
                            else
                            {
                                cC.envoyerMessage("Joueur " + joueurActuel.getId() + " a gagné la partie !");
                                cC.envoyerMessage(Commande.GAMENOTWIN.toString());
                            }
                        }
                        break;
                    }

                    // Un joueur gagne par défaut
                    if (listJoueurs.size() <=1)
                    {
                        jeuFini = true;
                        if (listJoueurs.size() == 1)
                        {
                            c.envoyerMessage("Vous gagnez par forfait !");
                            c.envoyerMessage(Commande.GAMEWIN.toString());
                        }
                    }

                    // Un joueur a perdu
                    if (joueurActuel.getNbPoints() == 0)
                    {
                        c.setGameOver(true);
                        listJoueurs.remove(joueurActuel);
                        listeConnexionClientPerdu.add(c);
                        c.envoyerMessage(Commande.GAMEOVER.toString());
                    }

                    // Envoie message joueur
                    if (joueurActuel.getNbPoints() != 0)
                        c.envoyerMessage("action");
                }

                // Suppression des clients déconnectés
                for (ConnexionClient c : listeConnexionClientPerdu)
                    listeConnexionClient.remove(c);

                listeConnexionClientPerdu.clear();
            }
        }

        if (jeu.getGagnant() != null)
            System.out.println("Le joueur " + jeu.getGagnant().getId() + " a gagné la partie !");
        else if (listJoueurs.size() == 1)
            System.out.println("Le joueur " + listJoueurs.get(0).getId() + " gagne par forfait !");
        else
            System.out.println("Match nul !");
    }

    @Override
    public void update(Observable obv, Object obj) {

        if(obj instanceof Joueur){
            Infraction infra = jeu.getCarte().getInfraction(((Joueur) obj).getPosX(),((Joueur) obj).getPosY(), ((Joueur) obj).getDirection());
            if(infra.equals(Infraction.COURBE)&&((Joueur) obj).getVitesse()>2){
                System.out.println("Amis1");
                System.out.println("Joueur mort.");
                ((Joueur) obj).setEtatMarche(false);
            }
            else if(infra.equals(Infraction.PRIORITE)){
                System.out.println("Amis2");
                //Si vitesse du joueur est de 3, perte de majorinf points
                if(((Joueur) obj).getVitesse()>2){
                    ((Joueur) obj).setNbPoints(((Joueur) obj).getNbPoints()-Constante.MAJORINF);
                }
                //Si vitesse du joueur égale à 2, perte de minorinf points
                else if(((Joueur) obj).getVitesse()==2){
                    ((Joueur) obj).setNbPoints(((Joueur) obj).getNbPoints()-Constante.MINORINF);
                }
            }
            else{
                System.out.println("c'était null négro");
            }

        }
    }
}