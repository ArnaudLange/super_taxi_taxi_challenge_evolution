package proto;

import static jeu.Utils.randInt;

/**
 * Created by Cokral on 03/05/2016.
 */
public class Serveur {

    private int port;
    private Carte carte;

    public Serveur(int port) {

        this.carte=new Carte();
        System.out.println("Chargement de la carte.");

        this.port = port;
        System.out.println("Connection du serveur sur le port " + port);

    }

    public void inscription(Joueur j){
        System.out.println("Inscription du joueur "+j);
    }

    public void sendInfo(){
        System.out.println("Le serveur envoie les informations nécessaires au joueur.");
    }

    public void getInfo(){
        System.out.println("Le serveur envoie une requête au joueur pour obtenir les informations d'action.");
    }

    public int gererInfo(){
        int random = randInt(5);
        if(random == 1){
            System.out.println("\tle joueur a atteint l'objectif.");
            System.out.println("Le serveur envoie au joueur qu'il a gagné.");
            System.out.println("\n-------\nYou Win\n-------");
            return 1;
        }
        else if(random == 2){
            System.out.println("\tle joueur se deplace de x case dans la direction souhaitee.");
            return 0;
        }
        else if(random == 3){
            System.out.println("\tle joueur effectue une action impossible et a un accident.");
            System.out.println("Le serveur envoie au joueur qu'il a perdu.");
            System.out.println("\n---------\nGame Over\n---------");
            return 1;
        }
        else if(random == 4){
            System.out.println("\tle joueur a un accident avec un autre joueur et perd la partie.");
            System.out.println("Le serveur envoie au joueur qu'il a perdu.");
            System.out.println("\n---------\nGame Over\n---------");
            return 1;
        }
        else if(random == 5){
            System.out.println("\tle joueur roule trop vite et perd x points.");
            return 0;
        }
        return 0;
    }
}
