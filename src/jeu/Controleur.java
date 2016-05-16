package jeu;

import carte.Carte;
import carte.InterpreteurCarte;
import carte.Route;
import carte.Virage;
import réseau.Serveur;

import java.util.ArrayList;

import static jeu.Utils.randInt;

/**
 * Created by Cokral on 16/05/2016.
 */
public class Controleur {

    public static void main(String[] args) {

        Carte carte = InterpreteurCarte.Interpreter("fileCarte.txt");
        Jeu jeu = new Jeu(carte);
        Serveur serv = new Serveur();


        Joueur j1 = new Joueur("Jose");
        serv.getJoueurs(j1, (ArrayList<Joueur>) jeu.getListeJoueurs());
        Joueur j2 = new Joueur("René");
        serv.getJoueurs(j2, (ArrayList<Joueur>) jeu.getListeJoueurs());

        boolean etatJeu=true;

        while(etatJeu){
            String action="";
            for (Joueur k : jeu.getListeJoueurs()) {
                action = serv.gererActionJoueur(k, serv.envoieRequeteJoueur(k));
                if (action == "rien"){
                    System.out.println("Pas d'action a effectuer.");
                    serv.envoieInfoJoueurs(k, k.getNbPoints(), k.getVitesse(), k.isEtatMarche(), k.getPosX(), k.getPosY());
                }
                if (action == "accelerer"){
                    if (jeu.getCarte().getCase(k.getPosX(),k.getPosY()) instanceof Virage ) {
                        int randAcc=randInt(3);
                        if(randAcc == 1){
                            System.out.println("Le joueur accelere et effectue son virage sans rouler trop vite.");
                            serv.envoieInfoJoueurs(k, k.getNbPoints(), k.getVitesse()+1, k.isEtatMarche(), k.getPosX(), k.getPosY());
                        }
                        else if (randAcc == 2){
                            System.out.println("Le joueur accelere mais roule trop vite lors de son virage et perd X points.");
                            serv.envoieInfoJoueurs(k, k.getNbPoints()-3, k.getVitesse()+1, k.isEtatMarche(), k.getPosX(), k.getPosY());
                        }
                        else if (randAcc == 3){
                            System.out.println("Le joueur accelere mais roule trop vite lors de son virage et a un accident.");
                            serv.envoieInfoJoueurs(k, k.getNbPoints(), k.getVitesse()+1, false, k.getPosX(), k.getPosY());
                        }
                    }
                    else if (jeu.getCarte().getCase(k.getPosX(),k.getPosY()) instanceof Route) {
                        System.out.println("Le joueur accelere.");
                        serv.envoieInfoJoueurs(k, k.getNbPoints(), k.getVitesse()+1, false, k.getPosX(), k.getPosY());
                    }
                }
                else{
                    jeu.getCarte().getCase(k.getPosX(),k.getPosY());
                }

            }

            etatJeu = false;
            System.out.println(etatJeu);
        }






    }
}
