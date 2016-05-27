package jeu;

import carte.Carte;
import carte.Infraction;
import carte.InterpreteurCarte;
import carte.PointCardinal;
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



        carte.addObserver(this);

        Joueur j = new Joueur();
        j.setPosX(0);
        j.setPosY(0);
        j.setVitesse(4);
        j.setDirection(PointCardinal.EAST);

        List<Joueur> listos = new ArrayList<Joueur>();
        listos.add(j);

        jeu = new Jeu(listos, carte);

        jeu.getCarte().gestionDeplacements(j);


        /*
        List<Joueur> listJoueurs = new ArrayList<>();
        Jeu jeu = new Jeu(listJoueurs, carte);
        List<ConnexionClient> listeConnexionClient = new ArrayList();
        Serveur.creerServeur(jeu.getListeJoueurs(), listeConnexionClient);
        Vector positionDepart = InterpreteurCarte.trouverPositionDepart(carte);

        for (Joueur joueur : listJoueurs)
        {
            int[] position = InterpreteurCarte.choisirPositionDepart(positionDepart);
            int posX = position[0];
            int posY = position[1];
            System.out.println("La position en X du joueur est " + posX);
            System.out.println("La position en Y du joueur est " + posY);
            joueur.setPosX(posX);
            joueur.setPosY(posY);
        }

        int nbTour=1;
        int nbJoueurOut = 0;
        boolean jeuFini = false;
        int tempsTour = 5000;
        long tempsDebutTour = System.currentTimeMillis();
        long tempsFinTour = tempsDebutTour + tempsTour;

        while(!jeuFini)
        {
            // Temps du tour atteint
            if (System.currentTimeMillis() >= tempsFinTour)
            {
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

                    for (Joueur k : listJoueurs)
                        if(c.getJoueur().getPosX() == k.getPosX() && c.getJoueur().getPosY() == k.getPosY())
                            c.getJoueur().setNbPoints(0); // TODO envoyer aux clients colisions

                    nbJoueurOut = 0;
                    c.envoyerMessage("action");
                }

                // On relance un tour
                tempsDebutTour = System.currentTimeMillis();
                tempsFinTour = tempsDebutTour + tempsTour;
            }
        }
        */
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