package proto;

/**
 * Created by Cokral on 03/05/2016.
 */
public class Test {

    public static void main(String[] args) {
        Serveur communication = new Serveur(50000);
        Joueur jose = new Joueur("Jose");
        communication.inscription(jose);

        System.out.println("Debut de la partie");


        for (int i = 0; i < 10; i++) {
            System.out.println("------------------------------------");
            communication.sendInfo();
            System.out.println("Le joueur joue : ");
            jose.joue();

            System.out.println("Le serveur gère les requetes du joueur : ");
            communication.gererInfo();
            try {
                Thread.sleep(1000);                 //1000 milliseconds is one second.
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
