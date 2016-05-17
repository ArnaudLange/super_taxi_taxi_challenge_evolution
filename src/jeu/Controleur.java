package jeu;

import carte.*;
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

pense
        Joueur j1 = new Joueur("Jose");
        serv.getJoueurs(j1, (ArrayList<Joueur>) jeu.getListeJoueurs());
        Joueur j2 = new Joueur("René");
        serv.getJoueurs(j2, (ArrayList<Joueur>) jeu.getListeJoueurs());

        boolean etatJeu=true;

        String action="";
        for (Joueur k : jeu.getListeJoueurs()) {
            action = serv.gererActionJoueur(k, serv.envoieRequeteJoueur(k));
            if (action == "rien"){
                System.out.println("Pas d'action a effectuer.");
                serv.envoieInfoJoueurs(k, k.getNbPoints(), k.getVitesse(), k.isEtatMarche(), k.getPosX(), k.getPosY());
            }
            else {
                Case caseActuelle = jeu.getCarte().getCase(k.getPosX(),k.getPosY());
                if (caseActuelle instanceof Virage ) {
                    int randVir=randInt(3);
                    if(randVir == 1){
                        System.out.println("Le joueur effectue son virage sans rouler trop vite.");
                        serv.envoieInfoJoueurs(k, k.getNbPoints(), k.getVitesse()+1, k.isEtatMarche(), k.getPosX(), k.getPosY());
                    }
                    else if (randVir == 2){
                        System.out.println("Le joueur roule trop vite lors de son virage et perd X points.");
                        serv.envoieInfoJoueurs(k, k.getNbPoints()-3, k.getVitesse()+1, k.isEtatMarche(), k.getPosX(), k.getPosY());
                    }
                    else if (randVir == 3){
                        System.out.println("Le joueur roule trop vite lors de son virage et a un accident.");
                        serv.envoieInfoJoueurs(k, k.getNbPoints(), k.getVitesse()+1, false, k.getPosX(), k.getPosY());
                    }
                }
                else if (caseActuelle instanceof Route) {
                    System.out.println("Le joueur se deplace sur la case.");
                    serv.envoieInfoJoueurs(k, k.getNbPoints(), k.getVitesse()+1, false, k.getPosX(), k.getPosY());
                }
                else if (caseActuelle instanceof RouteEnT){

                }
            }
        }






    }
}
