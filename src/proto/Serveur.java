package proto;

import static proto.Utils.randInt;

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
        System.out.println("Envoie des informations relatives au joueur.");
    }

    public void getInfo(){
        System.out.println("Demande au joueur les informations requises.");
    }

    public void gererInfo(){
        int random = randInt(5);
        if(random == 1){
            System.out.println("\tle joueur a atteint l'objectif");
            System.out.println("You Win");
        }
        else if(random == 2){
            System.out.println("\tle joueur se deplace de x case dans la direction souhaitee");
        }
        else if(random == 3){
            System.out.println("\tle joueur effectue une action impossible et a un accident");
        }
        else if(random == 4){
            System.out.println("\tle joueur a un accident avec un autre joueur et perd la partie");
            System.out.println("Game Over");
        }
        else if(random == 5){
            System.out.println("\tle joueur roule trop vite et perd x points");
        }
    }
}
