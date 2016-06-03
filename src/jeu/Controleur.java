package jeu;

import carte.*;
import reseau.ConnexionClient;
import reseau.Serveur;

import java.io.File;
import java.util.*;

/**
 * Created by jimmy on 14/05/16.
 */
public class Controleur implements Observer {

    private Jeu jeu;
    private final String pathFichierCarte = "src/carte/cartetest.txt";
    private Carte carte;
    private List<Joueur> listJoueurs;
    private List<ConnexionClient> listeConnexionClient;
    private List<ConnexionClient> listeConnexionClientPerdu;


    public Controleur()
    {
        this.carte = InterpreteurCarte.Interpreter(new File(pathFichierCarte));
        assert carte != null;
        this.listJoueurs = new ArrayList<>();
        this.jeu = new Jeu(listJoueurs, carte);
        Vector positionDispo = InterpreteurCarte.trouverPositionDepart(jeu.getCarte());
        int[] position = InterpreteurCarte.choisirPositionDepart(positionDispo);
        this.jeu.setPosXObjectif(position[0]);
        this.jeu.setPosYObjectif(position[1]);
        System.out.println("x="+jeu.getPosXObjectif()+"y="+jeu.getPosYObjectif());
        carte.posXO=jeu.getPosXObjectif();
        carte.posYO=jeu.getPosYObjectif();

        this.jeu.getCarte().initFeux();
        this.jeu.getCarte().initStops();
        this.jeu.getCarte().addObserver(this);
        this.listeConnexionClient = new ArrayList<>();
        this.listeConnexionClientPerdu = new ArrayList<>();
    }

    public void lancerJeu()
    {
        Serveur.creerServeur(jeu.getListeJoueurs(), listeConnexionClient);
        Vector positionDepart = InterpreteurCarte.trouverPositionDepart(carte);

        for (Joueur joueur : listJoueurs)
        {
            int[] position = InterpreteurCarte.choisirPositionDepart(positionDepart);
            joueur.setPosX(position[1]);
            joueur.setPosY(position[0]);
            joueur.setNbPoints(Constante.STARTPOINTS);
            //joueur.setDirection(PointCardinal.SOUTH);
            joueur.setVitesse(0);

            System.out.println("\nInitialisation du joueur : "+joueur.getNom());
            System.out.println("\tpos : " + joueur.getPosX() + "," + joueur.getPosY());
            System.out.println("\tdirection : "+joueur.getDirection());
            System.out.println("\tvitesse : "+joueur.getVitesse());
            System.out.println("\tnb points : "+joueur.getNbPoints());

        }

        int nbTour=1;
        boolean jeuFini = false;
        long tempsTour = Constante.TOURTIME;
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
                System.out.println("Position Objectif : "+jeu.getPosYObjectif()+", "+jeu.getPosXObjectif()+"\n");
                for (ConnexionClient c : listeConnexionClient)
                {
                    System.out.println("\nNom du joueur : " + c.getJoueur().getNom());
                    if (c.getJoueur().getDirection() != null){
                        jeu.getCarte().gestionDeplacements(c.getJoueur());
                    }

                    System.out.println("\tpos : " + c.getJoueur().getPosX() + "," + c.getJoueur().getPosY());
                    System.out.println("\tdirection : " + c.getJoueur().getDirection());
                    System.out.println("\tvitesse : " + c.getJoueur().getVitesse());
                    System.out.println("\tnb points : " + c.getJoueur().getNbPoints());

                    c.envoyerMessage("pos:" + c.getJoueur().getPosX() + "," + c.getJoueur().getPosY());
                    c.envoyerMessage("dir:" + c.getJoueur().getDirection());
                    c.envoyerMessage("vit:" + c.getJoueur().getVitesse());
                    c.envoyerMessage("nbp:" + c.getJoueur().getNbPoints());

                    c.envoyerMessage("carte:"+jeu.getCarte().getVision(c.getJoueur(), (ArrayList<Joueur> )listJoueurs));
                    /*if("test".equals(c.getJoueur().getNom())) {
                        AffichageCarte.affichageCarte(carte.GetVision(c.getJoueur().getPosY(), c.getJoueur().getPosX(), c.getJoueur()), c.getJoueur().getPosY(), c.getJoueur().getPosX(), c.getJoueur().getDirection());
                    }*/
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

                    // Gestion collision joueur
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
                        AffichageCarte.fermerFenetre();
                        break;
                    }



                    // Un joueur a perdu
                    if (joueurActuel.getNbPoints() == 0)
                    {
                        c.setGameOver(true);
                        listJoueurs.remove(joueurActuel);
                        listeConnexionClientPerdu.add(c);
                        c.envoyerMessage(Commande.GAMEOVER.toString());
                    }

                    // Un joueur gagne par défaut
                    if (listJoueurs.size() <=1)
                    {
                        jeuFini = true;
                        if (listJoueurs.size() == 1)
                        {
                            c.envoyerMessage(Commande.GAMEWINLASTPLAYER.toString());
                        }
                        AffichageCarte.fermerFenetre();
                    }

                    // Envoie message joueur
                    if (joueurActuel.getNbPoints() != 0)
                        c.envoyerMessage("action");
                }

                // Suppression des clients déconnectés
                for (ConnexionClient c : listeConnexionClientPerdu)
                    if (listeConnexionClient.contains(c))
                        listeConnexionClient.remove(c);
                if(!jeuFini) {
                    AffichageCarte.affichageCarte(carte.getTableau(), (ArrayList<Joueur>) listJoueurs, jeu.getPosXObjectif(), jeu.getPosYObjectif());
                    jeu.getCarte().updateFeux();
                }
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
            //System.out.println(infra);
            if(infra.contains(Evenement.OBJECTIF)){
                //
                // System.out.println("on est sur l'objectif");
                ((Joueur)obj).setNbPoints( ((Joueur)obj).getNbPoints() + Constante.OBJPOINTS );

                Vector positionDispo = InterpreteurCarte.trouverPositionDepart(jeu.getCarte());
                int[] newPositionObj = InterpreteurCarte.choisirPositionDepart(positionDispo);
                jeu.setPosXObjectif(newPositionObj[0]);
                jeu.setPosYObjectif(newPositionObj[1]);
                carte.posXO=jeu.getPosXObjectif();
                carte.posYO=jeu.getPosYObjectif();
                //System.out.println("NOUVEL OBJECTIF");
                //System.out.println("obj"+carte.posXO+","+carte.posYO);

            }
            if(infra.contains(Evenement.COURBE)&&((Joueur) obj).getVitesse()>2){
                //System.out.println("Joueur mort.");
                ((Joueur) obj).setNbPoints(0);
            }
            if(infra.contains(Evenement.FEU)){
                System.out.println("feu détecté");
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
                //System.out.println("Priorite a droite.");
                //Si vitesse du joueur est de 3, perte de majorinf points
                if(((Joueur) obj).getVitesse()>2){
                    ((Joueur) obj).setNbPoints(((Joueur) obj).getNbPoints()-Constante.MAJORINF);
                }
                //Si vitesse du joueur égale à 2, perte de minorinf points
                else if(((Joueur) obj).getVitesse()==2){
                    ((Joueur) obj).setNbPoints(((Joueur) obj).getNbPoints()-Constante.MINORINF);
                }
            }
            if(infra.size()==0){
                System.out.println("Pas d'événement.");
            }

        }
    }
}