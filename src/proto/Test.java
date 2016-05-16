package proto;

import jeu.*;
import carte.*;

/**
 * Created by Cokral on 03/05/2016.
 */
public class Test {

    public static void main(String[] args) {
        Serveur communication = new Serveur(50000);
        Joueur jose = new Joueur("Jose");
        communication.inscription(jose);

        System.out.println("Debut de la partie.");

        int i=0;
        while(i!=1){
            System.out.println("------------------------------------");
            communication.sendInfo();
            System.out.println("Le joueur joue : ");
            jose.joue();
            System.out.println("Le joueur envoie ses actions au serveur.");

            System.out.println("Le serveur gère les actions du joueur : ");
            i=communication.gererInfo();
            try {
                Thread.sleep(1000);                 //1000 milliseconds is one second.
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
