package jeu;

import carte.Carte;
import carte.Evenement;
import carte.Feu;
import carte.InterpreteurCarte;
import carte.PointCardinal;
import carte.Route;
import carte.Stop;
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


        File fichierCarte = new File("src/carte/carte2.txt"); // on initialise le fichier texte de la carte
        Carte carte = InterpreteurCarte.Interpreter(fichierCarte);

        assert carte != null;
        carte.addObserver(this);

        List<Joueur> listJoueurs = new ArrayList<>();
        jeu = new Jeu(listJoueurs, carte);
        jeu.setPosXObjectif(Constante.OBJECTIFCELL[0]);
        jeu.setPosYObjectif(Constante.OBJECTIFCELL[1]);
        jeu.getCarte().initFeux();
        jeu.getCarte().initStops();
        jeu.getCarte().addObserver(this);
        List<ConnexionClient> listeConnexionClient = new ArrayList<>();
        List<ConnexionClient> listeConnexionClientPerdu = new ArrayList<>();
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
            joueur.setPosX(posY);
            joueur.setPosY(posX);
            joueur.setNbPoints(Constante.STARTPOINTS);
            //joueur.setDirection(PointCardinal.SOUTH);
            joueur.setVitesse(0);

            System.out.println("\nInitialisation du joueur : "+joueur.getNom());
            System.out.println("\tpos : " + joueur.getPosX() + "," + joueur.getPosY());
            System.out.println("\tdirection : "+joueur.getDirection());
            System.out.println("\tvitesse : "+joueur.getVitesse());
            System.out.println("\tnb points : "+joueur.getNbPoints());

            i++;
        }



        int nbTour=1;
        boolean jeuFini = false;
        long tempsTour = 10000;
        long tempsDebutTour = System.currentTimeMillis();
        Joueur joueurActuel;

        while(!jeuFini)
        {
            // Temps du tour atteint
            if (System.currentTimeMillis() - tempsDebutTour > tempsTour)
            {
                System.out.println("\n----------------------------------");
                System.out.println("Tour " + nbTour++);
                System.out.println("----------------------------------");
                tempsDebutTour = System.currentTimeMillis();

                for (ConnexionClient c : listeConnexionClient)
                {
                    System.out.println("\nTour du joueur : " + c.getJoueur().getNom());
                    if (c.getJoueur().getDirection() != null){
                        jeu.getCarte().gestionDeplacements(c.getJoueur());
                    }
                    System.out.println("\nTour du joueur : " + c.getJoueur().getNom());
                    System.out.println("\tpos : " + c.getJoueur().getPosX() + "," + c.getJoueur().getPosY());
                    System.out.println("\tdirection : " + c.getJoueur().getDirection());
                    System.out.println("\tvitesse : " + c.getJoueur().getVitesse());
                    System.out.println("\tnb points : " + c.getJoueur().getNbPoints());

                    c.envoyerMessage("pos:" + c.getJoueur().getPosX() + "," + c.getJoueur().getPosY());
                    c.envoyerMessage("dir:" + c.getJoueur().getDirection());
                    c.envoyerMessage("vit:" + c.getJoueur().getVitesse());
                    c.envoyerMessage("nbp:" + c.getJoueur().getNbPoints());

                    /*
                    System.out.println("La vitesse du joueur "+ c.getJoueur().getNom() + " est " + c.getJoueur().getVitesse());
                    System.out.println("La direction du joueur "+ c.getJoueur().getNom() + " est " + c.getJoueur().getDirection());
                    System.out.println("Le nombre de points du joueur " + c.getJoueur().getNom() + " est " + c.getJoueur().getNbPoints());
                    System.out.println("La position en X du joueur "+ c.getJoueur().getNom() + " est " + c.getJoueur().getPosX());
                    System.out.println("La position en Y du joueur "+ c.getJoueur().getNom() + " est " + c.getJoueur().getPosY());
                    */
                }

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
                        	if(k.getVitesse()>1 || joueurActuel.getVitesse()>1){
	                            System.out.println("Colision entre le joueur " + joueurActuel.getId() + " et le joueur " + k.getId());
	                            System.out.println("Le joueur " + joueurActuel.getId() + " a perdu.");
	                            System.out.println("Le joueur " + k.getId() + " a perdu.");

	                            joueurActuel.setNbPoints(0);
	                            k.setNbPoints(0);
                        	}
                        }
                    }

                    // Un joueur a gagné
                    if (joueurActuel.getNbPoints() >= Constante.MAXPOINTS)
                    {
                        System.out.println("Partie gagnée !");
                        jeu.setGagnant(joueurActuel);
                        jeuFini = true;

                        for (ConnexionClient cC : listeConnexionClient)
                        {
                            if (jeu.getGagnant().equals(cC.getJoueur()))
                                cC.envoyerMessage(Commande.GAMEWINOBJ.toString());
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
                            c.envoyerMessage(Commande.GAMEWINLASTPLAYER.toString());
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
                jeu.getCarte().updateFeux();
            }

        }

        if (jeu.getGagnant() != null)
            System.out.println("Le joueur " + jeu.getGagnant().getId() + " a gagné la partie !");
        else if (listJoueurs.size() == 1)
            System.out.println("Le joueur " + listJoueurs.get(0).getId() + " gagne car c'est le dernier joueur en vie  !");
        else
            System.out.println("Match nul !");
    }

    @Override
    public void update(Observable obv, Object obj) {

        if(obj instanceof Joueur){
            List<Evenement> infra = jeu.getCarte().getEvenement(((Joueur) obj).getPosX(),((Joueur) obj).getPosY(), ((Joueur) obj).getDirection(), jeu.getPosXObjectif(), jeu.getPosYObjectif());

            if(infra.contains(Evenement.OBJECTIF)){
                ((Joueur)obj).setNbPoints( ((Joueur)obj).getNbPoints() + Constante.OBJPOINTS );

                if(((Joueur)obj).getNbPoints() >= Constante.MAXPOINTS){
                    System.out.println("Joueur a récupéré l'objectif.");
                    ((Joueur) obj).setNbPoints(((Joueur) obj).getNbPoints()+Constante.OBJPOINTS);
                } else{
                    Vector positionDispo = InterpreteurCarte.trouverPositionDepart(jeu.getCarte());
                    int[] newPositionObj = InterpreteurCarte.choisirPositionDepart(positionDispo);
                    jeu.setPosXObjectif(newPositionObj[0]);
                    jeu.setPosYObjectif(newPositionObj[1]);
                }

            }
            if(infra.contains(Evenement.COURBE)&&((Joueur) obj).getVitesse()>2){
                System.out.println("Joueur mort.");
                ((Joueur) obj).setEtatMarche(false);
            }
            if(infra.contains(Evenement.FEU)){
            	if(((Feu)jeu.getCarte().getTableau()[((Joueur) obj).getPosY()][((Joueur) obj).getPosX()]).getCouleurFeu(((Joueur) obj).getDirection())){
            		((Joueur) obj).setNbPoints(((Joueur) obj).getNbPoints()-Constante.MAJORINF);
            	}
            }else if(infra.contains(Evenement.STOP)){
            	if(((Stop)jeu.getCarte().getTableau()[((Joueur) obj).getPosY()][((Joueur) obj).getPosX()]).isAStopDirection(((Joueur) obj).getDirection())){
            		if(((Joueur) obj).getVitesse()==0){
            			((Joueur) obj).setHasStoped(true);
            		}else{
            			if(((Joueur) obj).getHasStoped()){
            				((Joueur) obj).setHasStoped(false);
            			}else{
            				((Joueur) obj).setNbPoints(((Joueur) obj).getNbPoints()-Constante.MAJORINF);
            			}
            		}
            	}
            }else if(infra.contains(Evenement.PRIORITE)){
                System.out.println("Priorite a droite.");
                //Si vitesse du joueur est de 3, perte de majorinf points
                if(((Joueur) obj).getVitesse()>2){
                	System.out.println("vitesse > 2, joueur trop rapide, perde de MAJORINF");
                    ((Joueur) obj).setNbPoints(((Joueur) obj).getNbPoints()-Constante.MAJORINF);
                }
                //Si vitesse du joueur égale à 2, perte de minorinf points
                else if(((Joueur) obj).getVitesse()==2){
                	System.out.println("vitesse == 2, joueur trop rapide, perde de MINORINF");
                    ((Joueur) obj).setNbPoints(((Joueur) obj).getNbPoints()-Constante.MINORINF);
                }
            }
            if(infra.size()==0){
                System.out.println("Pas d'événement.");
            }

        }
    }
}